package ee.avastaeesti.gameback.persistence.randomgamelocation;

import ee.avastaeesti.gameback.persistence.location.Location;
import ee.avastaeesti.gameback.persistence.randomgame.RandomGame;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "random_game_location", schema = "game")
public class RandomGameLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "random_game_id", nullable = false)
    private RandomGame randomGame;

    @NotNull
    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;

    @Column(name = "time_start")
    private Instant timeStart;

    @Column(name = "time_end")
    private Instant timeEnd;

}