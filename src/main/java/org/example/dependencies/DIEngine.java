package org.example.dependencies;

import org.example.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DIEngine {

    private static DIEngine instance = null;

    //Mapa metoda i instanca njihovih klasa kontrolera
    private Map<Method, Object> methodMap = new HashMap<>();

    //Mapa instanca dependency-ja anotiranih sa @Bean(scope = "singleton")
    private Map<Class, Object> dependencyMap = new HashMap<>();

    private DIEngine(){

    }

    public Object initializeController(Class controller) throws Exception {
        Object obj = null;
        obj = initializeDependecies(controller);
        return obj;
    }

    //recursive
    public Object initializeDependecies(Class dependecyClass) throws Exception {

        Object obj = null;

        if(dependecyClass.isAnnotationPresent(Controller.class)){
            obj = dependecyClass.getDeclaredConstructor().newInstance();
        }
        //Proverimo da li je klasa oznacena sa @Bean a nije @Controller
        else if(!(dependecyClass.isAnnotationPresent(Bean.class) || dependecyClass.isAnnotationPresent(Service.class) ||
                dependecyClass.isAnnotationPresent(Component.class)) && !dependecyClass.isAnnotationPresent(Controller.class)) {
            throw new Exception("Annotation missing");
        }
        //Ako jeste @Bean proverimo scope
        else if (dependecyClass.isAnnotationPresent(Bean.class)) {
            Bean beanAnnotation = (Bean) dependecyClass.getAnnotation(Bean.class);
            if(beanAnnotation.scope().equals("singleton")){
                //izvuci ga iz mape ako je tu
                if(dependencyMap.containsKey(dependecyClass)){
                    obj = dependencyMap.get(dependecyClass);
                    System.out.println("izvucen dependency iz mape (singleton): " + dependecyClass.getName());
                    return obj;
                }
                else{
                    obj = dependecyClass.getDeclaredConstructor().newInstance();
                    dependencyMap.put(dependecyClass, obj);
                    System.out.println("napravljen novi dependency (singleton): " + dependecyClass.getName());
                }
            }
            else if(beanAnnotation.scope().equals("prototype")){
                //instanciraj novi dependancy
                obj = dependecyClass.getDeclaredConstructor().newInstance();
                System.out.println("napravljen novi dependency (prototype): " + dependecyClass.getName());
            }
        }
        else if(dependecyClass.isAnnotationPresent(Service.class)){
            //izvuci ga iz mape ako je tu
            if(dependencyMap.containsKey(dependecyClass)){
                obj = dependencyMap.get(dependecyClass);
                System.out.println("izvucen dependency iz mape (singleton): " + dependecyClass.getName());
                return obj;
            }
            else{
                obj = dependecyClass.getDeclaredConstructor().newInstance();
                dependencyMap.put(dependecyClass, obj);
                System.out.println("napravljen novi dependency (singleton): " + dependecyClass.getName());
            }
        }
        else if(dependecyClass.isAnnotationPresent(Component.class)){
            //instanciraj novi dependancy
            obj = dependecyClass.getDeclaredConstructor().newInstance();
            System.out.println("napravljen novi dependency (prototype): " + dependecyClass.getName());
        }


        //Iteriramo kroz sve atribute klase dependancyClass i pretrazujemo one anotirane sa @Autowired
        Field[] fields = dependecyClass.getDeclaredFields();
        for(Field f: fields){
            if(f.isAnnotationPresent(Autowired.class)){
                Autowired autowired = f.getAnnotation(Autowired.class);

                Class dependency = f.getType();

                Object objDependancy = initializeDependecies(dependency);

                if(autowired.verbose()){
                    System.out.println("Initialized <" + f.getType() + "> <" + f.getName() + "> in <" +
                            f.getDeclaringClass().getName() + "> on <" + LocalDateTime.now() + "> with <" + objDependancy.hashCode() + ">");
                }

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
