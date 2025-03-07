package ee.avastaeesti.gameback.controller.location;

import ee.avastaeesti.gameback.controller.location.dto.LocationDto;
import ee.avastaeesti.gameback.controller.location.dto.LocationImage;
import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.infrastructure.error.ApiError;
import ee.avastaeesti.gameback.service.location.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addLocation(@RequestBody LocationDto locationDto) {
        locationService.addLocation(locationDto);
    }

    @GetMapping("/locations")
    @Operation(summary = "Toob asukohtade nimed ja id-d listina (nt: ripploendi jaoks)")
    public List<LocationInfo> getLocations() {
        List<LocationInfo> locations = locationService.getLocations();
        return locations;
    }

    @GetMapping("/location-preview")
    @Operation(summary = "Toob asukohtade nimed ja pildi asukoha id põhjal")
    public ResponseEntity<LocationImage> getLocationPreview(@RequestParam Integer locationId) {
        LocationImage locationPreview = locationService.getLocationPreview(locationId);
        return ResponseEntity.ok(locationPreview);
    }

    @PutMapping("/location")
    @Operation(summary = "Asukoha info muutmine questionId abil")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Ei leidnud primary keyd (errorCode 115)", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public void updateLocation(@RequestParam Integer locationId, @RequestBody LocationDto locationDto) {
        locationService.updateLocation(locationId, locationDto);
    }

//    @DeleteMapping("/location")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK"),
//            @ApiResponse(responseCode = "404", description = "Ei leidnud primary keyd (errorCode 115)", content = @Content(schema = @Schema(implementation = ApiError.class))),
//    })
//    public void deleteLocation(@RequestParam Integer questionId) {
//        locationService.deleteLocation(questionId);
//    }
}
