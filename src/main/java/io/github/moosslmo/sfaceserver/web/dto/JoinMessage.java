package io.github.moosslmo.sfaceserver.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class JoinMessage {
    private String username;
    private int score;
    private Coordinate coords;
}
