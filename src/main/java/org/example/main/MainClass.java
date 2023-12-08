package org.example.main;

import org.example.discovery.DirectoryCrawler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainClass {


    private Map<String, Method> routeMap = new HashMap<>();

    private static MainClass single_instance = null;

    private MainClass(){

    }

    public void analyze() throws Exception {
        DirectoryCrawler directoryCrawler = new DirectoryCrawler();
        directoryCrawler.discover();


        for (Map.Entry<String, Method> entry : routeMap.entrySet()) {

            String route = entry.getKey();
            Method method = entry.getValue();
            Class klasa = entry.getValue().getDeclaringClass();

            System.out.println("Route: " + route);
            System.out.println("Metoda: " + method.getName());
            System.out.println("Klasa: " + klasa.getName());
            System.out.println("=======================");



        }

    }

    public static synchronized MainClass getInstance()
    {
        if (single_instance == null)
            single_instance = new MainClass();

        return single_instance;
    }

    public Map<String, Method> getRouteMap() {
        return routeMap;
    }

    public void setRouteMap(Map<String, Method> routeMap) {
        this.routeMap = routeMap;
    }


}
