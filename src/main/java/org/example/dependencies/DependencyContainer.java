package org.example.dependencies;

import org.example.annotations.Qualifier;

import java.util.HashMap;
import java.util.Map;

public class DependencyContainer {

    private static DependencyContainer instance = null;

    private Map<String, Object> interfaceImplementations = new HashMap<>();

    private DependencyContainer(){}


    public void setImplementation(Class cl) {
        if(cl.isAnnotationPresent(Qualifier.class)){

        }
    }

    public static synchronized DependencyContainer getInstance() {

        if(instance == null){
            instance = new DependencyContainer();
        }

        return instance;
    }
}
