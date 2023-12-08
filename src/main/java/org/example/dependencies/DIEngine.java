package org.example.dependencies;

import org.example.annotations.Autowired;
import org.example.annotations.Bean;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
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

    public Object initializeController(Class controller) throws Exception {
        Object obj = null;
        obj = initializeDependecies(controller);
        return obj;
    }

    //recursive
    public Object initializeDependecies(Class dependecyClass) throws Exception {

        //instanciramo controller/dependency
        Object obj = null;
        obj = dependecyClass.getDeclaredConstructor().newInstance();

        //Iteriramo kroz sve atribute klase dependancyClass i pretrazujemo one anotirane sa @Autowired
        Field[] fields = dependecyClass.getDeclaredFields();
        for(Field f: fields){
            if(f.isAnnotationPresent(Autowired.class)){
                Class dependency = f.getType();

                //Proverimo da li je klasa tog polja oznacena sa @Bean
                if(!dependency.isAnnotationPresent(Bean.class)) throw new Exception("@Bean annotation missing");

                Object objDependancy = initializeDependecies(dependency);

                //nasetujemo instancu dependency-ja na ovo polje
                f.setAccessible(true);
                f.set(obj, objDependancy);
            }
        }
        return obj;
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
