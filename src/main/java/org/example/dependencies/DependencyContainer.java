package org.example.dependencies;

import org.example.annotations.Qualifier;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {

    private static DependencyContainer instance = null;

    private Map<String, Object> interfaceImplementations = new HashMap<>();

    private DependencyContainer(){}


    public void setImplementation(Class cl) throws Exception{
        if(cl.isAnnotationPresent(Qualifier.class)) {
            Qualifier qu = (Qualifier) cl.getAnnotation(Qualifier.class);
            String value = qu.value();
            Object obj = cl.getDeclaredConstructor().newInstance();
            interfaceImplementations.put(value, obj);
        }
    }

    public static synchronized DependencyContainer getInstance() {

        if(instance == null){
            instance = new DependencyContainer();
        }

        return instance;
    }
}
