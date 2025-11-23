package ru.otus.homework.patternAdapter.valueObject;

import lombok.Data;

//@Bean(value = "Movable.Position.get")
@Data
public class Point {

    private int coordinateX;

    private int coordinateY;

    public Point(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Point moveTo(Vector velocity) {
        setCoordinateX(getCoordinateX() + velocity.getCoordinateDX());
        setCoordinateY(getCoordinateY() + velocity.getCoordinateDY());
        return this;
    }

}
