package org.example.controllers;

import org.example.annotations.Controller;
import org.example.annotations.GET;
import org.example.annotations.POST;
import org.example.annotations.Path;

@Controller
public class StudentController {

    @GET
    @Path("/students")
    public int allStudents(){
        return 0;
    }

    @POST
    @Path("/add")
    public void addStudent(String student){

    }

    @GET
    @Path("/find/{student}")
    public int findStudent(String student){
        return 0;
    }
}
