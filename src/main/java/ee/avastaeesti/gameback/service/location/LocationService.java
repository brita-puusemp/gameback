package ee.avastaeesti.gameback.service.location;

import ee.avastaeesti.gameback.controller.location.dto.LocationDto;
import ee.avastaeesti.gameback.controller.location.dto.LocationImage;
import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.infrastructure.exception.ForbiddenException;
import ee.avastaeesti.gameback.persistence.location.Location;
import ee.avastaeesti.gameback.persistence.location.LocationMapper;
import ee.avastaeesti.gameback.persistence.location.LocationRepository;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static ee.avastaeesti.gameback.infrastructure.Error.LOCATION_EXISTS;
import static ee.avastaeesti.gameback.status.Status.DELETED;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public void addLocation(LocationDto locationDto) {
        boolean locationExists = locationRepository.locationExistsBy(locationDto.getLocationName());
        if (locationExists) {
            throw new ForbiddenException(LOCATION_EXISTS.getMessage(), LOCATION_EXISTS.getErrorCode());
        }
        Location location = locationMapper.toLocation(locationDto);
        locationRepository.save(location);
    }

    public List<LocationInfo> getLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationInfo> locationDtos = locationMapper.toLocationInfos(locations);
        return locationDtos;
    }

    public void updateLocation(Integer locationId, LocationDto locationDto) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("locationId", locationId));
        locationMapper.updateLocation(locationDto, location);
        locationRepository.save(location);
    }

    public LocationDto getLocation(Integer locationId) {
        Location locationForEdit = locationRepository.findById(locationId).orElseThrow();
        LocationDto locationFilled = locationMapper.toLocationDto(locationForEdit);
        return locationFilled;
    }

    public LocationImage getLocationPreview(Integer locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow();
        LocationImage locationImage = locationMapper.toLocationImage(location);
        return locationImage;
    }

    public void removeLocation(Integer locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("locationId", locationId));
        location.setStatus(DELETED.getCode());
        locationRepository.save(location);
    }
}