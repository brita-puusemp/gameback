package ee.avastaeesti.gameback.service.game;

import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.persistence.game.Game;
import ee.avastaeesti.gameback.persistence.game.GameMapper;
import ee.avastaeesti.gameback.persistence.game.GameRepository;
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
    private final GameMapper gameMapper;


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
