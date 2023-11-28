package org.example.main;


import org.example.controllers.MyAbstractController;
import org.example.discovery.DirectoryCrawler;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public class Main {



    public static void main(String[] args) throws ClassNotFoundException {

        DirectoryCrawler directoryCrawler = new DirectoryCrawler();
        directoryCrawler.discover();


    }



}