package ee.avastaeesti.gameback.service.gethint;

import org.springframework.stereotype.Service;

@Service
public class GetHintService {

    public String getLocationHint(int questionId) {
        return "Hint for question ID: " + questionId;
    }
}