package ee.avastaeesti.gameback.service.usergame;

import ee.avastaeesti.gameback.controller.randomgame.dto.NextRandomLocation;
import ee.avastaeesti.gameback.infrastructure.Error;
import ee.avastaeesti.gameback.infrastructure.exception.DataNotFoundException;
import ee.avastaeesti.gameback.persistence.game.Game;
import ee.avastaeesti.gameback.persistence.game.GameRepository;
import ee.avastaeesti.gameback.persistence.gamelocation.GameLocation;
import ee.avastaeesti.gameback.persistence.gamelocation.GameLocationRepository;
import ee.avastaeesti.gameback.persistence.location.LocationMapper;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import ee.avastaeesti.gameback.persistence.usergame.UserGame;
import ee.avastaeesti.gameback.persistence.usergame.UserGameRepository;
import ee.avastaeesti.gameback.persistence.usergamelocation.UserGameLocation;
import ee.avastaeesti.gameback.persistence.usergamelocation.UserGameLocationMapper;
import ee.avastaeesti.gameback.persistence.usergamelocation.UserGameLocationRepository;
import ee.avastaeesti.gameback.status.GameState;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCreatedGameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final UserGameRepository userGameRepository;
    private final GameLocationRepository gameLocationRepository;
    private final UserGameLocationRepository userGameLocationRepository;
    private final UserGameLocationMapper userGameLocationMapper;
    private final LocationMapper locationMapper;


    public Integer createNewUserGame(Integer gameId, Integer userId) {
        Game userCreatedGame = gameRepository.findById(gameId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("gameId", gameId));


        User user = userRepository.findById(userId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("userId", userId));

        long totalLocations = gameLocationRepository.countTotalLocationsBy(gameId);


        //TODO Loob uue randmon game tabeli
        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGame(userCreatedGame);
        userGame.setTotalLocations((int) totalLocations);
        userGame.setLocationsAnswered(0);
        userGame.setTotalScore(0);
        userGame.setCorrectAnswers(0);
        userGame.setIsComplete(false);

        userGameRepository.save(userGame);


        List<GameLocation> gameLocations = gameLocationRepository.findGameLocationsBy(gameId);
        ArrayList<UserGameLocation> userGameLocations = new ArrayList<>();

        for (GameLocation gameLocation : gameLocations) {
            UserGameLocation userGameLocation = new UserGameLocation();
            userGameLocation.setLocation(gameLocation.getLocation());
            userGameLocation.setGame(userCreatedGame);
            userGameLocation.setUserGame(userGame);
            userGameLocation.setUser(user);
            userGameLocation.setIsCorrect(false);
            if (userGameLocations.isEmpty()) {
                userGameLocation.setState(GameState.NEXT_LOCATION.getCode());
            } else {
                userGameLocation.setState(GameState.LOCATION_PENDING.getCode());
            }
            userGameLocations.add(userGameLocation);
        }

        userGameLocationRepository.saveAll(userGameLocations);

        //tagastame fronti random game gameId
        return userGame.getId();

    }

    public NextRandomLocation getNextUserGameLocation(Integer userGameId) {

        UserGame userGame = userGameRepository.findById(userGameId).orElseThrow();


        // Kindlusta, et refreshi vajutades ei tule uut rida, kui eelmine location on vastamata ehk tagasta fronti location, mille state on AP (answer pending)
        Optional<UserGameLocation> answerPendingUserGameLocation = userGameLocationRepository.findNextLocationBy(userGameId, GameState.ANSWER_PENDING.getCode());
        if (answerPendingUserGameLocation.isPresent()) {
            UserGameLocation userGameLocation = answerPendingUserGameLocation.get();
            NextRandomLocation nextUserGameLocation = userGameLocationMapper.toNextRandomLocation(userGameLocation);
            nextUserGameLocation.setIsGameComplete(userGame.getIsComplete());
            return nextUserGameLocation;
        }

        //Otsi järgmine location, mille state on NL (next location)
        UserGameLocation userGameNextLocation = userGameLocationRepository.findNextLocationBy(userGameId, GameState.NEXT_LOCATION.getCode())
                .orElseThrow(() -> new DataNotFoundException(Error.NO_RANDOM_LOCATION_FOUND.getMessage(), Error.NO_RANDOM_LOCATION_FOUND.getErrorCode()));


        //Muuda leitud location state AP (answer pending)
        userGameNextLocation.setState(GameState.ANSWER_PENDING.getCode());
        userGameLocationRepository.save(userGameNextLocation);

        //Otsi järgmine asukoht, mille state on LP (Location Pending), ja muuda selle state NL (next location)
        //LP puudub kui hangitakse viimast locationi

        Optional<UserGameLocation> locationPendingUserGameLocation = userGameLocationRepository
                .findNextLocationBy(userGameId, GameState.LOCATION_PENDING.getCode());
        if (locationPendingUserGameLocation.isPresent()) {
            UserGameLocation nextLocationPending = locationPendingUserGameLocation.get();
            nextLocationPending.setState(GameState.NEXT_LOCATION.getCode());
            userGameLocationRepository.save(nextLocationPending);
        }

        // Tagasta järgmise asukoha andmed
        NextRandomLocation nextUserGameLocation = locationMapper.toNextRandomLocation(userGameNextLocation.getLocation());
        nextUserGameLocation.setIsGameComplete(userGame.getIsComplete());

        return nextUserGameLocation;
    }
}
