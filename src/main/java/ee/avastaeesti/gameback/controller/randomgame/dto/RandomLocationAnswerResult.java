package ee.avastaeesti.gameback.controller.randomgame.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RandomLocationAnswerResult {
    private String locationName;
    private Boolean locationIsCorrect;
    private Boolean gameIsComplete;
    private Integer totalQuestions;
    private Integer questionsAnswered;


}
