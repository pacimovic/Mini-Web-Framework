package org.example.dependencies;

import org.example.annotations.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DIEngine {

    private static DIEngine instance = null;

    //Mapa metoda i instanca njihovih klasa kontrolera
    private Map<Method, Object> methodMap = new HashMap<>();
    private List<Object> dependencies = new ArrayList<>();

    private DIEngine(){

    }

    public Object initializeController(Class controller){

        Object obj = null;

        try {
            obj = controller.getDeclaredConstructor().newInstance();

            initializeDependecies(controller);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    //recursive
    public void initializeDependecies(Class dependecyClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Iteriramo kroz sve atribute klase dependancyClass i pretrazujemo one anotirane sa @Autowired
        Field[] fields = dependecyClass.getDeclaredFields();
        for(Field f: fields){
            if(f.isAnnotationPresent(Autowired.class)){
                Class dependency = f.getType();
                initializeDependecies(dependency);

                Object obj = dependency.getDeclaredConstructor().newInstance();
                dependencies.add(obj);
            }
        }
    }

    public Map<Method, Object> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<Method, Object> methodMap) {
        this.methodMap = methodMap;
    }

    public static synchronized DIEngine getInstance(){
        if(instance == null){
            instance = new DIEngine();
        }

        return instance;
    }
}
