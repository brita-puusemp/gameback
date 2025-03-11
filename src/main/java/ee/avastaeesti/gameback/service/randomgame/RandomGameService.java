package ee.avastaeesti.gameback.service.randomgame;

import ee.avastaeesti.gameback.controller.randomgame.dto.NextRandomLocation;
import ee.avastaeesti.gameback.infrastructure.Error;
import ee.avastaeesti.gameback.infrastructure.exception.DataNotFoundException;
import ee.avastaeesti.gameback.persistence.location.Location;
import ee.avastaeesti.gameback.persistence.location.LocationMapper;
import ee.avastaeesti.gameback.persistence.location.LocationRepository;
import ee.avastaeesti.gameback.persistence.randomgame.RandomGame;
import ee.avastaeesti.gameback.persistence.randomgame.RandomGameRepository;
import ee.avastaeesti.gameback.persistence.randomgamelocation.RandomGameLocation;
import ee.avastaeesti.gameback.persistence.randomgamelocation.RandomGameLocationMapper;
import ee.avastaeesti.gameback.persistence.randomgamelocation.RandomGameLocationRepository;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import ee.avastaeesti.gameback.status.GameState;
import ee.avastaeesti.gameback.status.Status;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RandomGameService {


    private final UserRepository userRepository;
    private final RandomGameRepository randomGameRepository;
    private final LocationRepository locationRepository;
    private final RandomGameLocationRepository randomGameLocationRepository;
    private final LocationMapper locationMapper;
    private final RandomGameLocationMapper randomGameLocationMapper;

    public Integer createNewRandomGame(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("userId", userId));
//TODO Loob uue randmon game tabeli
        RandomGame randomGame = new RandomGame();
        randomGame.setUser(user);
        randomGame.setTotalLocations(5);
        randomGame.setLocationsAnswered(0);
        randomGame.setIsComplete(false);

        randomGameRepository.save(randomGame);
//lisame random gameile random locations
        List<Location> randomLocations = locationRepository.findRandomLocationsBy(Status.ACTIVE.getCode(), PageRequest.of(0, 5));
        List<RandomGameLocation> randomGameLocations = new ArrayList<>();

        for (Location randomLocation : randomLocations) {

            RandomGameLocation randomGameLocation = new RandomGameLocation();
            randomGameLocation.setRandomGame(randomGame);
            randomGameLocation.setLocation(randomLocation);
            randomGameLocation.setIsCorrect(false);
//määrame ära mängualguse iga locationi State (NL, LP)
            if (randomGameLocations.isEmpty()) {
                randomGameLocation.setState(GameState.NEXT_LOCATION.getCode());
            } else {
                randomGameLocation.setState(GameState.LOCATION_PENDING.getCode());
            }
            randomGameLocations.add(randomGameLocation);
        }

        randomGameLocationRepository.saveAll(randomGameLocations);
//tagastame fronti random game gameId
        return randomGame.getId();

    }

    public NextRandomLocation getNextRandomLocation(Integer randomGameId) {
        RandomGame randomGame = randomGameRepository.findById(randomGameId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("randomGameId", randomGameId));


//    kindlusta, et reftrshi vajutades ei tule uut rida, kui eelmine locatio no vastamata(AP - on andmebaasis)
        Optional<RandomGameLocation> answerPendingRandomGameLocation = randomGameLocationRepository.findRandomGameLocationBy(randomGameId, GameState.ANSWER_PENDING.getCode());
        if (answerPendingRandomGameLocation.isPresent()) {
            RandomGameLocation randomGameLocation = answerPendingRandomGameLocation.get();
            NextRandomLocation nextRandomLocation = randomGameLocationMapper.toNextRandomLocation(randomGameLocation);
            nextRandomLocation.setIsGameComplete(randomGame.getIsComplete());
            return nextRandomLocation;
        }

//NL - > AP´ks (get teenuse sisend),
        RandomGameLocation randomGameLocation = randomGameLocationRepository.findRandomGameLocationBy(randomGameId, GameState.NEXT_LOCATION.getCode())
                .orElseThrow(() -> new DataNotFoundException(Error.NO_RANDOM_LOCATION_FOUND.getMessage(), Error.NO_RANDOM_LOCATION_FOUND.getErrorCode()));

        randomGameLocation.setState(GameState.ANSWER_PENDING.getCode());
        randomGameLocation.setTimeStart(Instant.now());
        randomGameLocationRepository.save(randomGameLocation);

        NextRandomLocation nextRandomLocation = locationMapper.toNextRandomLocation(randomGameLocation.getLocation());
        nextRandomLocation.setIsGameComplete(randomGame.getIsComplete());
        nextRandomLocation.setTimeStart(randomGameLocation.getTimeStart());


        RandomGameLocation nextLocationPending = randomGameLocationRepository.findFirstByRandomGameIdAndStateOrderByIdAsc(randomGameId, GameState.LOCATION_PENDING.getCode()).orElseThrow();
        nextLocationPending.setState(GameState.NEXT_LOCATION.getCode());
        randomGameLocationRepository.save(nextLocationPending);

        return nextRandomLocation;
    }
}
