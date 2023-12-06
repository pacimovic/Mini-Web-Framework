package org.example.controllers;

import org.example.annotations.Controller;
import org.example.annotations.GET;
import org.example.annotations.Param;
import org.example.annotations.Path;

@Controller
public class NewsController extends MyAbstractController{

    @GET
    @Path("/news")
    public int allNews(){
        return 0;
    }

    @GET
    @Path("/news/find")
    public int findNews(@Param("student") String student){
        System.out.println("Pokrenuta metoda GET /findNews sa parametrom student: " + student);
        return 0;
    }
}
