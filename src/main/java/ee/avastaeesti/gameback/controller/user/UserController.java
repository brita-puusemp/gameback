package ee.avastaeesti.gameback.controller.user;

import ee.avastaeesti.gameback.controller.user.dto.NewUser;
import ee.avastaeesti.gameback.controller.user.dto.UserDto;
import ee.avastaeesti.gameback.infrastructure.error.ApiError;
import ee.avastaeesti.gameback.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @Operation(summary = "Uue kasutaja loomine")
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

    @GetMapping("/user-profile")
    @Operation(summary = "Kuvab kasutaja andmeid UserId abil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Ei leidnud primary keyd (errorCode 115)", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public UserDto getUser(@RequestParam Integer userId) {
        UserDto userById = userService.findUserById(userId);
        return userById;
    }

    @PutMapping("/user-profile-edit")
    @Operation(summary = "Kasutaja info muutmine userId abil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Ei leidnud primary keyd (errorCode 115)", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
//    todo: siin tuleks nagu userDto ju mängu ka, aga viskab viga ja ei viska servisiesse meetodit
    public void updateUserProfile(@RequestParam Integer UserId) {
        userService.findUserById(UserId);
    }
}
