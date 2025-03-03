package ee.avastaeesti.gameback.persistence.randomgame;

import ee.avastaeesti.gameback.persistence.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "random_game", schema = "game")
public class RandomGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "total_questions", nullable = false)
    private Integer totalQuestions;

    @NotNull
    @Column(name = "questions_answered", nullable = false)
    private Integer questionsAnswered;

    @NotNull
    @Column(name = "is_complete", nullable = false)
    private Boolean isComplete = false;

}