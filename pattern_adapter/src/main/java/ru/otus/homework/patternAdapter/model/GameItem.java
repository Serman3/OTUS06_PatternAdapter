package ru.otus.homework.patternAdapter.model;

public interface GameItem {

    Object getValue(String key);

    void addValue(String key, Object value);
}
