package ee.avastaeesti.gameback.controller.game.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.avastaeesti.gameback.persistence.leaderboard.LeaderBoard}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardDto implements Serializable {
    private Integer gameId;
    private String gameName;
    private String gameDescription;
    @NotNull
    private Integer totalTopScore;
}