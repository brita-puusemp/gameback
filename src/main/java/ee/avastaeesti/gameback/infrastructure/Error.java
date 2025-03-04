package ee.avastaeesti.gameback.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    INCORRECT_CREDENTIALS("Vale kasutajanimi või parool", 111),
    USERNAME_EXISTS("Sellise nimega kasutaja on juba olamas", 112),
    EMAIL_EXISTS("Sellise e-mailiga kasutaja on juba süsteemis olemas", 113),
    PRIMARY_KEY_NOT_FOUND("Ei leidnud primary keyd: ", 888),
    FOREIGN_KEY_NOT_FOUND("Ei leidnud foreign keyd: ", 999);

    private final String message;
    private final int errorCode;
}
