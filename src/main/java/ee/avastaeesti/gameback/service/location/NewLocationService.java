package ee.avastaeesti.gameback.service.location;

import ee.avastaeesti.gameback.controller.location.dto.NewLocation;
import ee.avastaeesti.gameback.infrastructure.exception.ForbiddenException;
import ee.avastaeesti.gameback.persistence.question.LocationMapper;
import ee.avastaeesti.gameback.persistence.question.LocationRepository;
import ee.avastaeesti.gameback.persistence.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ee.avastaeesti.gameback.infrastructure.Error.LOCATION_EXISTS;

@Service
@RequiredArgsConstructor
public class NewLocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public void addNewLocation(NewLocation newLocation) {
        boolean locationExists = locationRepository.locationExistsBy(newLocation.getLocationName());
        if (locationExists) {
            throw new ForbiddenException(LOCATION_EXISTS.getMessage(), LOCATION_EXISTS.getErrorCode());
        }

        Question question = locationMapper.toQuestion(newLocation);
        locationRepository.save(question);
    }


}

