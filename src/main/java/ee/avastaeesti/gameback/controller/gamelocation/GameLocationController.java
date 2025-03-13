package ee.avastaeesti.gameback.controller.gamelocation;

import ee.avastaeesti.gameback.controller.gamelocation.dto.GameLocationInfo;
import ee.avastaeesti.gameback.service.gamelocation.GameLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameLocationController {

    private final GameLocationService gameLocationService;

    @PostMapping("/game-location")
    public void addGameLocation(@RequestParam Integer gameId,@RequestParam Integer locationId) {
        gameLocationService.addGameLocation(gameId,locationId);
    }

    @GetMapping("/game-locations")
    public List<GameLocationInfo> getGameLocations(@RequestParam Integer gameId){
        return gameLocationService.getGameLocations(gameId);

    }
}
