package ru.otus.homework.patternAdapter.action;

import ru.otus.homework.patternAdapter.valueObject.Point;
import ru.otus.homework.patternAdapter.valueObject.Vector;

public interface Movable {

    Point getPosition();

    void setPosition(Point newValue);

    Vector getVelocity();

    void finish();

}
