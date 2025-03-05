package ee.avastaeesti.gameback.controller.location;

import ee.avastaeesti.gameback.controller.location.dto.NewLocation;
import ee.avastaeesti.gameback.infrastructure.error.ApiError;
import ee.avastaeesti.gameback.service.location.NewLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationContoller {

    private final NewLocationService newLocationService;

    @PostMapping("/newLocation")
    @Operation(summary = "Uue asukoha lisamine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "403", description = "Sellise nimega asukoht on juba süsteemis olemas", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })

    public void addNewLocation(@RequestBody NewLocation newLocation) {
        newLocationService.addNewLocation(newLocation);
    }

}
