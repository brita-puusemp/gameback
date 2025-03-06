package ee.avastaeesti.gameback.controller.location;

import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.infrastructure.error.ApiError;
import ee.avastaeesti.gameback.service.location.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/location")
    @Operation(summary = "Uue asukoha lisamine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Sellise nimega asukoht on juba süsteemis olemas", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })

    public void addNewLocation(@RequestBody LocationInfo locationInfo) {
        locationService.addNewLocation(locationInfo);
    }

    @PutMapping("/location")
    @Operation(summary = "Asukoha info muutmine questionId abil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Ei leidnud primary keyd (errorCode 888)", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public void updateLocation(@RequestParam Integer questionId, @RequestBody LocationInfo locationInfo) {
        locationService.updateLocatation(questionId, locationInfo);

    }
}
