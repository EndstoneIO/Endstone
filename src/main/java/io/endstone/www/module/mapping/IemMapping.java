package io.endstone.www.module.mapping;

import java.lang.reflect.Method;

public interface IemMapping {
    void register(String originClass, String originMethod, Class injectClass, Method injectMethod);
}
