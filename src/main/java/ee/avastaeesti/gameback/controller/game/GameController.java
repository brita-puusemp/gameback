package ee.avastaeesti.gameback.controller.game;

import ee.avastaeesti.gameback.controller.location.dto.LocationsInfo;
import ee.avastaeesti.gameback.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/locations")
    public List<LocationsInfo> getLocations() {
        List<LocationsInfo> locations = gameService.getLocations();
        return locations;

    }
}
