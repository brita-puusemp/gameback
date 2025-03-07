package ee.avastaeesti.gameback.service.game;

import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.persistence.game.Game;
import ee.avastaeesti.gameback.persistence.game.GameMapper;
import ee.avastaeesti.gameback.persistence.game.GameRepository;
import ee.avastaeesti.gameback.persistence.question.LocationInfoMapper;
import ee.avastaeesti.gameback.persistence.question.LocationRepository;
import ee.avastaeesti.gameback.persistence.question.Question;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final LocationInfoMapper locationInfoMapper;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public List<LocationInfo> getLocations() {
        List<Question> questions = locationRepository.findAll();
        List<LocationInfo> locationInfos = locationInfoMapper.toLocationInfos(questions);
        return locationInfos;


    }

    public Integer createGame(NewGame newGame) {
//        @Mapping(source = "EI SAA MÄPPIDA", target = "user")
        User user = userRepository.findById(newGame.getUserId())
                .orElseThrow(() -> ValidationService.throwForeignKeyNotFoundException("userId", newGame.getUserId()));

        Game game = gameMapper.toGame(newGame);
        game.setUser(user);
        gameRepository.save(game);
        return game.getId();
    }
}
