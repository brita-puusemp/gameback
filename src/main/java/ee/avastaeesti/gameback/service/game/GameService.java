package ee.avastaeesti.gameback.service.game;

import ee.avastaeesti.gameback.controller.location.dto.LocationsInfo;
import ee.avastaeesti.gameback.persistence.question.LocationInfoMapper;
import ee.avastaeesti.gameback.persistence.question.LocationRepository;
import ee.avastaeesti.gameback.persistence.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final LocationRepository locationRepository;
    private final LocationInfoMapper locationInfoMapper;

    public List<LocationsInfo> getLocations() {
        List<Question> questions = locationRepository.findAll();
        List<LocationsInfo> locationsInfos = locationInfoMapper.toLocationInfos(questions);
        return locationsInfos;


    }
}

/*List<Question> locations = locationRepository.findAll();
        locationInfoMapper.toLocationInfos(locations);
    }*/
