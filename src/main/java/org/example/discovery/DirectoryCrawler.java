package org.example.discovery;

import org.example.annotations.Controller;
import org.example.annotations.GET;
import org.example.annotations.POST;
import org.example.annotations.Path;
import org.example.main.Main;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DirectoryCrawler {

    private List<Class> controllerClasses = new ArrayList<>();

    public void discover() throws ClassNotFoundException {
        File file = new File("src/main/java/org/example");
        if(file.exists() && file.isDirectory()){
            crawl(file);
        }

        mapAnotationRoutes();

    }

    private void mapAnotationRoutes(){
        for(Class cl: controllerClasses){

            System.out.println("Metode klase: " + cl.getName());
            Method[] methods = cl.getDeclaredMethods();
            for(Method method: methods){
                System.out.println("metoda: " + method.getName());
                System.out.println("Njena klasa: " + method.getDeclaringClass().getName());
                if(method.isAnnotationPresent(GET.class) && method.isAnnotationPresent(Path.class)){
                    System.out.println("GET anotacija i PATH anotacija");
                }
                else if(method.isAnnotationPresent(POST.class) && method.isAnnotationPresent(Path.class)){
                    System.out.println("POST anotacija i PATH anotacija");
                }
                else{
                    System.err.println("Annotation is missing!");
                }
                System.out.println("--------------------------");
            }
            System.out.println("\n============================\n");
        }
    }

    private void crawl(File f) throws ClassNotFoundException {
        if (f.isDirectory()) {
            File[] subFiles = f.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                crawl(subFiles[i]);
            }
        }
        else{
            String path = f.getPath().replace('\\','.').replace("src.main.java.", "").replace(".java","");
            Class cl = Class.forName(path);

            if(!cl.isAnnotation() && cl.isAnnotationPresent(Controller.class)){
                controllerClasses.add(cl);
            }
        }
    }
}
