package ru.otus.homework.patternAdapter.model;

import ru.otus.homework.patternAdapter.valueObject.Point;
import ru.otus.homework.patternAdapter.valueObject.Vector;

import java.util.HashMap;
import java.util.Map;

//@Bean
public class GameItemImpl implements GameItem {

    private final Map<String, Object> storage = new HashMap<>();

  //  @Inject
    public GameItemImpl(Vector vector, Point point) {
        addValue("Velocity", vector);
        addValue("Position", point);
    }

    @Override
    public Object getValue(String key) {
        if (storage.containsKey(key)) {
            return storage.get(key);
        }
        throw new RuntimeException("Key not found");
    }

    @Override
    public void addValue(String key, Object value) {
        storage.put(key, value);
    }
}
