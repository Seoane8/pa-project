package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ScoreDto {

    public int score;

    @NotNull
    @Min(1)
    @Max(5)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
