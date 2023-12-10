package org.example.dependencies;

import org.example.annotations.Qualifier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {

    private static DependencyContainer instance = null;

    private Map<String, Class> interfaceImplementations = new HashMap<>();

    private DependencyContainer(){}


    public Class getImplementation(Field interfaceField) throws Exception {
        if(interfaceField.isAnnotationPresent(Qualifier.class)){
            Qualifier qu = interfaceField.getAnnotation(Qualifier.class);
            String value = qu.value();
            if(interfaceImplementations.containsKey(value)){
                return interfaceImplementations.get(value);
            }
            else{
                throw new Exception("Implementation doesn't exist for given field");
            }
        }
        else{
            throw new Exception("Missing annotation Qualifier!");
        }

    }

    public void setImplementation(Class cl) throws Exception {
        if(cl.isAnnotationPresent(Qualifier.class)) {
            Qualifier qu = (Qualifier) cl.getAnnotation(Qualifier.class);
            String value = qu.value();
            if(interfaceImplementations.containsKey(value)){
                throw new Exception("Bean with specific Qualifier already exist!");
            }
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
