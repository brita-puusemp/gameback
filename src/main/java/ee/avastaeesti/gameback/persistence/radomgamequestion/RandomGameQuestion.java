package ee.avastaeesti.gameback.persistence.radomgamequestion;

import ee.avastaeesti.gameback.persistence.question.Question;
import ee.avastaeesti.gameback.persistence.randomgame.RandomGame;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "random_game_questions", schema = "game")
public class RandomGameQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "random_game_id", nullable = false)
    private RandomGame randomGame;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @NotNull
    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect = false;

    @Column(name = "time_start")
    private Instant timeStart;

    @Column(name = "time_end")
    private Instant timeEnd;

}