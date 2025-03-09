package ee.avastaeesti.gameback.service.gethint;

import ee.avastaeesti.gameback.persistence.location.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class GetHintService {

    private final LocationRepository locationRepository;

    public GetHintService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String getLocationHint(int questionId) {
        String clue = locationRepository.findClueByQuestionId(questionId);
        return clue != null ? clue : "No hint found for question ID: " + questionId; //change to orelsethrow?
    }
}