package ee.avastaeesti.gameback.controller.game;

import ee.avastaeesti.gameback.controller.game.dto.GameData;
import ee.avastaeesti.gameback.controller.game.dto.GameInfo;
import ee.avastaeesti.gameback.controller.game.dto.NewGame;
import ee.avastaeesti.gameback.controller.game.dto.UserGame;
import ee.avastaeesti.gameback.service.game.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    //todo: SIIA KOPEERIMISEKS PANDUD, ET Api errorid EI UNUNEKS
//    @Operation(summary = "toob ära kasutaja andmed UserId abil")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "404", description = "Ei leidnud primary keyd (errorCode 115)", content = @Content(schema = @Schema(implementation = ApiError.class))),
//    })

    private final GameService gameService;

    @GetMapping("/games")
    @Operation(summary = "toob ära kõik mängud")
    public List<GameInfo> getAllGames() {
        List<GameInfo> allGames = gameService.getAllGames();
        return allGames;
    }

    @GetMapping("/user-games")
    @Operation(summary = "toob ära kõik kasutaja poolt loodud mängud userId abil")
    public List<UserGame> getUserGames(@RequestParam Integer userId) {
        List<UserGame> userGames = gameService.getUserGames(userId);
        return userGames;
    }

    @PostMapping("/game")
    @Operation(summary = "Lisab uue mängu ja tagastab kasutaja poolt loodud mängu gameId")
    public Integer createGame(@RequestBody NewGame newGame) {
        Integer gameId = gameService.createGame(newGame);
        return gameId;
    }

    @PostMapping("/game-save")
    @Operation(summary = "Lisab kasutaja poolt loodud mängule valitud asukohad")
    public void saveGame(@RequestBody GameData gameData) {
        gameService.saveGame(gameData);

    }

    @DeleteMapping("/user-game")
    @Operation(summary = "Kasutaja poolt loodud mängu eemaldamine nimekirjast gameId abil, andmebaasis status A=>D")
    public void removeUserGame(@RequestParam Integer gameId) {
        gameService.deleteUserGame(gameId);
    }

}
