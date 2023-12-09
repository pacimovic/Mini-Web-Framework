package org.example.controllers;

import org.example.annotations.*;
import org.example.services.NewsService;
import org.example.services.StudentService;

@Controller
public class NewsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private NewsService newsService;

    @GET
    @Path("/news")
    public int allNews(){
        this.newsService.allNews();
        return 0;
    }

    @GET
    @Path("/news/find")
    public int findNews(@Param("student") String student){
        System.out.println("Pokrenuta metoda GET /findNews sa parametrom student: " + student);
        return 0;
    }
}
