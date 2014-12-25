package io.endstone.www.module.mapping;

import io.endstone.www.annotation.EInit;
import io.endstone.www.annotation.EModule;
import io.endstone.www.module.config.IemConfig;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;
import java.util.*;

@EModule(name = "emMapping", require = {"emConfig"})
public class EMMapping implements IemMapping {

    private Map<CtMethod, List<MethodWrapper>> mapRegisteredMethod;
    private Map<Class<?>, Object> mapClassSingleton;

    @EInit
    public void init(IemConfig emConfig) {

    }

    @Override
    public void register(String originClass, String originMethod, Class injectClass, Method injectMethod) {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass ctClazz = cp.getCtClass(originClass);
            CtMethod ctMethod = ctClazz.getDeclaredMethod(originMethod);
            if (!mapClassSingleton.containsKey(injectClass)) {
                mapClassSingleton.put(injectClass, injectClass.newInstance());
            }
            if (!mapRegisteredMethod.containsKey(ctMethod)) {
                mapRegisteredMethod.put(ctMethod, Collections.synchronizedList(new ArrayList<MethodWrapper>()));
            }
            mapRegisteredMethod.get(ctMethod).add(new MethodWrapper(injectClass, injectMethod));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
