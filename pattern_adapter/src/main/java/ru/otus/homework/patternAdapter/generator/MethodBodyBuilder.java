package ru.otus.homework.patternAdapter.generator;

import ru.otus.homework.patternAdapter.command.Command;
import ru.otus.homework.patternAdapter.ioc.Ioc;

public class MethodBodyBuilder {

    private final String methodName;
    private final String interfaceName;
    private final String returnTypeName;

    public MethodBodyBuilder(String methodName, String interfaceName, String returnTypeName) {
        this.methodName = methodName;
        this.interfaceName = interfaceName;
        this.returnTypeName = returnTypeName;
    }

    public String getBody() {
        String propertyName = methodName.replaceAll("(get|set)", "");
        String res = methodName.contains("get")
                ? getterIocImplementationBody(returnTypeName, interfaceName, propertyName)
                : setterIocImplementationBody(Command.class.getName(), interfaceName, propertyName);
        return res.replaceAll("Ioc", Ioc.class.getName());
    }

    public String getterIocImplementationBody(String propertyTypeName, String interfaceName, String propertyName) {
        return String.format("return ((%s) Ioc.resolve(\"%s.%s.get\", new Object[]{obj}));", propertyTypeName, interfaceName, propertyName);
    }

    public String setterIocImplementationBody(String castTypeName, String interfaceName, String propertyName) {
        return String.format("((%s) Ioc.resolve(\"%s.%s.set\", new Object[]{obj, new Object()})).execute();", castTypeName, interfaceName, propertyName);
    }
}
