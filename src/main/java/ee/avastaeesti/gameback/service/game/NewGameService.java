package ee.avastaeesti.gameback.service.game;

import ee.avastaeesti.gameback.controller.location.dto.LocationInfo;
import ee.avastaeesti.gameback.persistence.question.LocationInfoMapper;
import ee.avastaeesti.gameback.persistence.question.LocationRepository;
import ee.avastaeesti.gameback.persistence.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewGameService {

    private final LocationRepository locationRepository;
    private final LocationInfoMapper locationInfoMapper;

    public List<LocationInfo> getLocations() {
        List<Question> questions = locationRepository.findAll();
        List<LocationInfo> locationInfos = locationInfoMapper.toLocationInfos(questions);
        return locationInfos;


    }
}

/*List<Question> locations = locationRepository.findAll();
        locationInfoMapper.toLocationInfos(locations);
    }*/
