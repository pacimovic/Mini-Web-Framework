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

            Method[] methods = cl.getDeclaredMethods();
            for(Method method: methods){
                if(method.isAnnotationPresent(GET.class) && method.isAnnotationPresent(Path.class)){
                    Path pathInfo = method.getAnnotation(Path.class);
                    if(!Main.routeMap.containsKey("GET@" + pathInfo.value())) Main.routeMap.put("GET@" + pathInfo.value(), method);
                    else System.err.println("Route already exists!");
                }
                else if(method.isAnnotationPresent(POST.class) && method.isAnnotationPresent(Path.class)){
                    Path pathInfo = method.getAnnotation(Path.class);
                    if(!Main.routeMap.containsKey("GET@" + pathInfo.value())) Main.routeMap.put("GET@" + pathInfo.value(), method);
                    else System.err.println("Route already exists!");
                }
                else{
                    System.err.println("Annotation is missing!");
                }

            }

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
