package ee.avastaeesti.gameback.controller.user;

import ee.avastaeesti.gameback.controller.user.dto.NewUser;
import ee.avastaeesti.gameback.infrastructure.error.ApiError;
import ee.avastaeesti.gameback.service.user.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Kasutaja loomine ebaõnnestus: "
                    + "Sellise nimega kasutaja on juba olemas (errorCode 112) või "
                    + "sellise e-mailiga kasutaja on juba süsteemis olemas (errorCode 113)",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public void addNewUser(@RequestBody NewUser newUser) {
        userService.addNewUser(newUser);
    }

}
