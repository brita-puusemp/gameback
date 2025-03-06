package ee.avastaeesti.gameback.controller.game;

import ee.avastaeesti.gameback.controller.location.dto.LocationsInfo;
import ee.avastaeesti.gameback.service.game.NewGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewGameController {

    private final NewGameService newGameService;

    @GetMapping("/locations")
    public List<LocationsInfo> getLocations() {
        List<LocationsInfo> locations = newGameService.getLocations();
        return locations;

    }
}
