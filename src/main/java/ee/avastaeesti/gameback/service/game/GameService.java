package ee.avastaeesti.gameback.service.game;

import ee.avastaeesti.gameback.controller.game.dto.GameData;
import ee.avastaeesti.gameback.controller.game.dto.LeaderBoardDto;
import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.infrastructure.exception.ForbiddenException;
import ee.avastaeesti.gameback.persistence.game.Game;
import ee.avastaeesti.gameback.persistence.game.GameMapper;
import ee.avastaeesti.gameback.persistence.game.GameRepository;
import ee.avastaeesti.gameback.persistence.gamelocation.GameLocation;
import ee.avastaeesti.gameback.persistence.gamelocation.GameLocationRepository;
import ee.avastaeesti.gameback.persistence.leaderboard.LeaderBoard;
import ee.avastaeesti.gameback.persistence.leaderboard.LeaderBoardRepository;
import ee.avastaeesti.gameback.persistence.location.Location;
import ee.avastaeesti.gameback.persistence.location.LocationRepository;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static ee.avastaeesti.gameback.infrastructure.Error.*;

@Service
@RequiredArgsConstructor
public class GameService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final LeaderBoardRepository leaderBoardRepository;
    private final GameLocationRepository gameLocationRepository;
    private final LocationRepository locationRepository;
    private final GameMapper gameMapper;

    public void saveGame(GameData gameData) {
        Game gameId = gameRepository.getReferenceById(gameData.getGameId());
        for (Integer locationId : gameData.getLocationIds()) {
            Location location = locationRepository.getReferenceById(locationId);

            GameLocation gameLocation = new GameLocation();
            gameLocation.setGame(gameId);
            gameLocation.setLocation(location);


            gameLocationRepository.save(gameLocation);

        }
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

    public ArrayList<LeaderBoardDto> getGames() {
        List<LeaderBoard> allGames = leaderBoardRepository.findAll();
        ArrayList<LeaderBoardDto> results = new ArrayList<>();

        for (LeaderBoard leaderBoard : allGames) {
/*            LeaderBoard userScore = leaderBoardRepository.findUserScoreBy(leaderBoard.getGame().getId()).orElseThrow(() -> new ForbiddenException(USER_SCORE_NOT_FOUND.getMessage(), USER_SCORE_NOT_FOUND.getErrorCode()));
            Integer totalUserScore = userScore.getTotalScore();*/
            LeaderBoard topScore = leaderBoardRepository.findTopScoreBy(leaderBoard.getGame().getId()).orElseThrow(() -> new ForbiddenException(TOP_SCORE_NOT_FOUND.getMessage(), TOP_SCORE_NOT_FOUND.getErrorCode()));
            Integer totalTopScore = topScore.getTotalScore();

            results.add(new LeaderBoardDto(leaderBoard.getGame().getId(), leaderBoard.getGame().getName(), leaderBoard.getGame().getDescription(), totalTopScore));
        }

        return results;

    }
}
