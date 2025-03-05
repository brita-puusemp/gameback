package ee.avastaeesti.gameback.service.location;

import ee.avastaeesti.gameback.controller.location.dto.NewLocation;
import ee.avastaeesti.gameback.persistence.question.LocationMapper;
import ee.avastaeesti.gameback.persistence.question.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewLocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public void addNewLocation(NewLocation newUser) {

    }

}
