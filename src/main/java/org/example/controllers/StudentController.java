package org.example.controllers;

import org.example.annotations.Controller;
import org.example.annotations.GET;
import org.example.annotations.POST;
import org.example.annotations.Path;

@Controller
public class StudentController extends MyAbstractController{

    @GET
    @Path("/students")
    public String allStudents(){
        System.out.println("Pokrenuta metoda GET /students IDEMOOOOO");
        return "";
    }

    @POST
    @Path("/add")
    public void addStudent(String student){

    }

    @GET
    @Path("/find")
    public int findStudent(String student){
        System.out.println("Pokrenuta metoda GET /find sa parametrom student: " + student);
        return 0;
    }

    @GET
    @Path("/students/jmbg")
    public void studentsJMBG(){

    }
}
