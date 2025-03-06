package ee.avastaeesti.gameback.service.location;

import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.infrastructure.exception.ForbiddenException;
import ee.avastaeesti.gameback.persistence.question.LocationMapper;
import ee.avastaeesti.gameback.persistence.question.LocationRepository;
import ee.avastaeesti.gameback.persistence.question.Question;
import ee.avastaeesti.gameback.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ee.avastaeesti.gameback.infrastructure.Error.LOCATION_EXISTS;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public Optional<Question> updateLocatation(Integer questionId, LocationInfo locationInfo) {
        Optional<Question> question = Optional.ofNullable(locationRepository.findLocationByQuestionId(questionId)
                .orElseThrow(() -> ValidationService.throwPrimaryKeyNotFoundException("questionId", questionId)));
        return question;
    }

    public void addNewLocation(LocationInfo locationInfo) {
        boolean locationExists = locationRepository.locationExistsBy(locationInfo.getLocationName());
        if (locationExists) {
            throw new ForbiddenException(LOCATION_EXISTS.getMessage(), LOCATION_EXISTS.getErrorCode());
        }
        Question question = locationMapper.toQuestion(locationInfo);
        locationRepository.save(question);
    }

}

