package io.github.moosslmo.sfaceserver.domain;

import io.github.moosslmo.sfaceserver.web.dto.Coordinate;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    private int score;

    private int highScore;

    public void setScore(int score) {
        this.score = score;
        if (score > this.highScore) {
            this.highScore = score;
        }
    }
}
