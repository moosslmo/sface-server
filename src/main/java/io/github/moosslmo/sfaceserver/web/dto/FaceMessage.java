package io.github.moosslmo.sfaceserver.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FaceMessage {
    private String username;
    private int score;
    private Coordinate coords;
    private String imageBase64;
}
