package io.github.moosslmo.sfaceserver.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Coordinate {
    private double x;
    private double y;
}
