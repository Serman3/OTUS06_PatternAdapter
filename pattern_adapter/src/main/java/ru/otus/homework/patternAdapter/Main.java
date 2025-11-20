package ru.otus.homework.patternAdapter;

import ru.otus.homework.patternAdapter.adapter.AdapterFactory;
import ru.otus.homework.patternAdapter.adapter.DynamicAdapterFactory;

public class Main {

    public static void main(String[] args) {

        AdapterFactory<Movable> movableAdapterFactory = DynamicAdapterFactory.createAdapterFactory(Movable.class);
        Movable movable = movableAdapterFactory.create(new Object());
        System.out.println(movable);

    }
}