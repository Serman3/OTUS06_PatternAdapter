package ru.otus.homework.patternAdapter.model;

import ru.otus.homework.patternAdapter.command.Command;
import ru.otus.homework.patternAdapter.generator.MethodBuilder;
import ru.otus.homework.patternAdapter.ioc.Ioc;

import java.util.function.Function;

public class GameItemMethodBodyBuilder implements MethodBuilder {

    @Override
    public String getBody(String methodName, String interfaceName, String returnTypeName) {
        String propertyName = methodName.replaceAll("(get|set)", "");
        String res = "";
        if (methodName.startsWith("get")) {
            res = getterIocBody(returnTypeName, interfaceName, propertyName).replaceAll("Ioc", Ioc.class.getName());
        } else if (methodName.startsWith("set")) {
            res = setterIocBody(Command.class.getName(), interfaceName, propertyName).replaceAll("Ioc", Ioc.class.getName());
        } else {
            res = getCustomBody(methodName, interfaceName, returnTypeName);
        }
        return res;
    }

    @Override
    public String getConstructorBody(String methodName, String interfaceName) {
        String propertyName = methodName.replaceAll("(get|set)", "");
        String getOrSet = methodName.replaceAll("^(get|set).*", "$1");
        String res = methodName.contains("get")
                ? registerGetDependencyIocBody(interfaceName, propertyName, getOrSet)
                : registerSetDependencyIocBody(interfaceName, propertyName, getOrSet);
        return res;
    }

    @Override
    public String getCustomBody(String methodName, String interfaceName, String returnTypeName) {
        return "System.out.println(\"FINISH\");";
    }

    private String registerGetDependencyIocBody(String interfaceName, String propertyName, String getOrSet) {
        return String.format("""
                ((%s) %s.resolve(
                           "IoC.Register",
                           new Object[]{"%s.%s.%s", (%s<Object[], Object>) (Object[] arg) -> ((%s) arg[0]).getValue("%s")})
                ).execute();
                """, Command.class.getName(), Ioc.class.getName(), interfaceName, propertyName, getOrSet, Function.class.getName(),  GameItem.class.getName(), propertyName);
    }

    private String registerSetDependencyIocBody(String interfaceName, String propertyName, String getOrSet) {
        return String.format("""
                ((%s) %s.resolve("IoC.Register",
                            new Object[]{"%s.%s.%s", (%s<Object[], Object>) (Object[] arg) -> {
                            %s gameItem = (%s) arg[0];
                            gameItem.addValue("%s", arg[1]);
                            return gameItem;
                }})
                ).execute();
                """, Command.class.getName(), Ioc.class.getName(), interfaceName, propertyName, getOrSet, Function.class.getName(), GameItem.class.getName(), GameItem.class.getName(), propertyName);
    }

    private String getterIocBody(String propertyTypeName, String interfaceName, String propertyName) {
        return String.format("return ((%s) Ioc.resolve(\"%s.%s.get\", new Object[]{obj}));", propertyTypeName, interfaceName, propertyName);
    }

    private String setterIocBody(String castTypeName, String interfaceName, String propertyName) {
        return String.format("Ioc.resolve(\"%s.%s.set\", new Object[]{obj, Point});", interfaceName, propertyName);
    }
}
