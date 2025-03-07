package ee.avastaeesti.gameback.controller.game;

import ee.avastaeesti.gameback.controller.location.dto.LocationsInfo;
import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.service.game.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/locations")
    public List<LocationInfo> getLocations() {
        List<LocationInfo> locations = gameService.getLocations();
        return locations;

    }

    @PostMapping("/game")
    @Operation(summary = "Lisab uue mängu ja tagastab täisarvuna lisatud mängu gameId")
    public Integer createGame(@RequestBody NewGame newGame) {
        Integer gameId = gameService.createGame(newGame);
        return gameId;

    }
}
