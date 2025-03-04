package ee.avastaeesti.gameback.controller.user;

import ee.avastaeesti.gameback.controller.user.dto.NewUser;
import ee.avastaeesti.gameback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void addNewUser(@RequestBody NewUser newUser) {
        userService.addNewUser(newUser);

    }

}
