package ee.avastaeesti.gameback.controller.usergame;

import ee.avastaeesti.gameback.controller.randomgame.dto.NextRandomLocation;
import ee.avastaeesti.gameback.service.usergame.UserCreatedGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCreatedGameController {

    private final UserCreatedGameService userCreatedGameService;

    @GetMapping("/user/game/location")
    public NextRandomLocation getNextUserGameLocation(@RequestParam Integer userGameId) {
        NextRandomLocation nextUserGameLocation = userCreatedGameService.getNextUserGameLocation(userGameId);
        return nextUserGameLocation;
    }

    @PostMapping("user/game")
    public Integer createNewUserGame(@RequestParam Integer gameId, Integer userId) {
        Integer newUserGame = userCreatedGameService.createNewUserGame(gameId, userId);
        return newUserGame;
    }


}

