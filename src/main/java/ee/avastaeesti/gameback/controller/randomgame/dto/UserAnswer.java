package ee.avastaeesti.gameback.controller.randomgame.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
    @NotNull
    private BigDecimal lat;
    @NotNull
    private BigDecimal lng;


}
