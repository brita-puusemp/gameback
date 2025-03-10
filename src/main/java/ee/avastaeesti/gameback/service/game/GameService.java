package ee.avastaeesti.gameback.service.game;

import ee.avastaeesti.gameback.controller.game.dto.GameData;
import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.persistence.game.Game;
import ee.avastaeesti.gameback.persistence.game.GameMapper;
import ee.avastaeesti.gameback.persistence.game.GameRepository;
import ee.avastaeesti.gameback.persistence.gamelocation.GameLocation;
import ee.avastaeesti.gameback.persistence.gamelocation.GameLocationRepository;
import ee.avastaeesti.gameback.persistence.location.Location;
import ee.avastaeesti.gameback.persistence.location.LocationRepository;
import ee.avastaeesti.gameback.persistence.user.User;
import ee.avastaeesti.gameback.persistence.user.UserRepository;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
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
}
