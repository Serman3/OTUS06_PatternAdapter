package ru.otus.homework.patternAdapter.valueObject;

import lombok.Data;

//@Bean(value = "Movable.Velocity.get")
@Data
public class Vector {

    private final int coordinateDX;

    private final int coordinateDY;

    public Vector(int coordinateDX, int coordinateDY) {
        this.coordinateDX = coordinateDX;
        this.coordinateDY = coordinateDY;
    }
}