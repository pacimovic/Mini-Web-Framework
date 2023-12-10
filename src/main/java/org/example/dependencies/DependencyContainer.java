package org.example.dependencies;

import org.example.annotations.Qualifier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {

    private static DependencyContainer instance = null;

    private Map<String, Class> interfaceImplementations = new HashMap<>();

    private DependencyContainer(){}


    public Class getImplementation(Field interfaceField) {
        if(interfaceField.isAnnotationPresent(Qualifier.class)){
            Qualifier qu = interfaceField.getAnnotation(Qualifier.class);
            String value = qu.value();
            if(interfaceImplementations.containsKey(value)){
                return interfaceImplementations.get(value);
            }
            else{
                //error
            }
        }
        else{
            //error
        }

        return null;
    }

    public void setImplementation(Class cl){
        if(cl.isAnnotationPresent(Qualifier.class)) {
            Qualifier qu = (Qualifier) cl.getAnnotation(Qualifier.class);
            String value = qu.value();
            interfaceImplementations.put(value, cl);
        }
    }

    public static synchronized DependencyContainer getInstance() {

        if(instance == null){
            instance = new DependencyContainer();
        }

        return instance;
    }
}
