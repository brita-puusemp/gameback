package ee.avastaeesti.gameback.controller.game;

import ee.avastaeesti.gameback.controller.game.dto.GameData;
import ee.avastaeesti.gameback.controller.game.dto.LeaderBoardDto;
import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.service.game.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/game")
    @Operation(summary = "Lisab uue mängu ja tagastab täisarvuna lisatud mängu gameId")
    public Integer createGame(@RequestBody NewGame newGame) {
        Integer gameId = gameService.createGame(newGame);
        return gameId;
    }

    @PostMapping("/game-save")
    public void saveGame(@RequestBody GameData gameData) {
        gameService.saveGame(gameData);

    }

    @GetMapping("/games")
    public ArrayList<LeaderBoardDto> getGames() {
        ArrayList<LeaderBoardDto> games = gameService.getGames();
        return games;
    }

}
