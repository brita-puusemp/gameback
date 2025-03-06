package ee.avastaeesti.gameback.controller.location.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ee.avastaeesti.gameback.persistence.question.Question}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo implements Serializable {
    @NotNull
    @Size(max = 255)
    private String locationName;

    @NotNull
    private BigDecimal longitude;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    @Size(max = 1000)
    private String clue;

    @NotNull
    private String imageData;
}