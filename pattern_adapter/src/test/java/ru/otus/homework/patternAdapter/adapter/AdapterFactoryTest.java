package ru.otus.homework.patternAdapter.adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.patternAdapter.action.Movable;
import ru.otus.homework.patternAdapter.command.Command;
import ru.otus.homework.patternAdapter.model.GameItemMethodBodyBuilder;
import ru.otus.homework.patternAdapter.ioc.Ioc;
import ru.otus.homework.patternAdapter.model.GameItem;
import ru.otus.homework.patternAdapter.model.GameItemImpl;
import ru.otus.homework.patternAdapter.scopes.InitCommand;
import ru.otus.homework.patternAdapter.valueObject.Point;
import ru.otus.homework.patternAdapter.valueObject.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdapterFactoryTest {

    private Movable movable;

    @BeforeEach
    void setUp() {
        new InitCommand().execute();
        Object iocScope = Ioc.resolve("IoC.Scope.Create", new Object[]{});
        ((Command) Ioc.resolve("IoC.Scope.Current.Set", new Object[]{iocScope})).execute();

        GameItem gameItem = new GameItemImpl(new Vector(3, 4), new Point(1, 2));

        AdapterFactory<Movable> movableAdapterFactory = Ioc.resolve("Adapter", new Object[]{Movable.class, new GameItemMethodBodyBuilder()});
        movable = movableAdapterFactory.create(gameItem);
    }

    @AfterEach
    void tearDown() {
        ((Command) Ioc.resolve("IoC.Scope.Current.Clear", null)).execute();
    }

    @Test
    public void adapterGetValueTest() {
        Point point = movable.getPosition();
        assertNotNull(point);

        Vector velocity = movable.getVelocity();
        assertNotNull(velocity);
    }

    @Test
    public void adapterSetValueTest() {
        Point updatedPoint = new Point(10, 10);
        movable.setPosition(updatedPoint);
        assertEquals(updatedPoint, movable.getPosition());
    }

}