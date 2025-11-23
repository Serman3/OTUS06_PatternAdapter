package ru.otus.homework.patternAdapter.adapter;

public interface AdapterFactory<T> {

    T create(Object obj);
}
