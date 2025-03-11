package ee.avastaeesti.gameback.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GameState {
    NEXT_LOCATION("NL"),
    ANSWER_PENDING("AP"),
    LOCATION_PENDING("LP");

    private final String code;
}
