package ru.otus.homework.patternAdapter.scopes;

public interface IDependencyResolver {

    Object resolve(String dependency, Object[] args);
}
