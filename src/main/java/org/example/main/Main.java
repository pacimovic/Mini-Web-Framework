package org.example.main;


import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException {


        File file = new File("src/main/java/org/example");
        if(file.exists() && file.isDirectory()){
            crawl(file);
        }

        Class mainClass = Main.class;
        Method[] methods = mainClass.getDeclaredMethods();
        for(Method method: methods){
            for(Annotation annotation: method.getAnnotations()){
                System.out.println(annotation.annotationType());
            }
        }

    }


    private static void crawl(File f) throws ClassNotFoundException {
        if (f.isDirectory()) {
            File[] subFiles = f.listFiles();
            for (int i = 0; i < subFiles.length; i++) {
                crawl(subFiles[i]);
            }
        }
        else{
            String path = f.getPath().replace('\\','.').replace("src.main.java.", "").replace(".java","");
            Class cl = Class.forName(path);


            System.out.println("Metode klase: " + cl.getName());
            Method[] methods = cl.getDeclaredMethods();
            for(Method method: methods){
                System.out.println("Anotacije metode: " + method.getName());
                for(Annotation annotation: method.getAnnotations()){
                    System.out.println(annotation.annotationType());
                }
                System.out.println("--------------------------");
            }
            System.out.println("\n============================\n");

        }
    }
}