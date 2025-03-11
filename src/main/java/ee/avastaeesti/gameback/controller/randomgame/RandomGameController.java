package ee.avastaeesti.gameback.controller.randomgame;

import ee.avastaeesti.gameback.controller.randomgame.dto.NextRandomLocation;
import ee.avastaeesti.gameback.controller.randomgame.dto.UserAnswer;
import ee.avastaeesti.gameback.service.randomgame.RandomGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RandomGameController {

    private final RandomGameService randomGameService;

    @PostMapping("/random/game")
    public Integer createNewRandomGame(@RequestParam Integer userId) {
       return randomGameService.createNewRandomGame(userId);
    }

    @GetMapping("/random/game/location")
    public NextRandomLocation getNextRandomLocation(@RequestParam Integer randomGameId) {
       return randomGameService.getNextRandomLocation(randomGameId);
    }

    @GetMapping("/game/location/result")
    public void getLocationResult(@RequestParam UserAnswer userAnswer) {
        randomGameService.getLocationResult(userAnswer);
    }

}
