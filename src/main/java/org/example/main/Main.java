package org.example.main;


import org.example.controllers.MyAbstractController;
import org.example.discovery.DirectoryCrawler;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String, Method> routeMap = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException {

        DirectoryCrawler directoryCrawler = new DirectoryCrawler();
        directoryCrawler.discover();

        for (Map.Entry<String, Method> entry : routeMap.entrySet()) {
            System.out.println("Route: " + entry.getKey());
            System.out.println("Metoda: " + entry.getValue().getName());
            System.out.println("Klasa: " + entry.getValue().getDeclaringClass().getName());
            System.out.println("=======================");
        }


    }



}