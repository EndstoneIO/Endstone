package io.endstone.www.module.mapping;

import java.lang.reflect.Method;

public class MethodWrapper {

    public final Class clazz;
    public final Method method;

    public MethodWrapper(Class clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }
}
