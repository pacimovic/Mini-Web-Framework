package org.example.controllers;

import org.example.annotations.*;

@Controller
public class StudentController {

    @GET
    @Path("/students")
    public String allStudents(){
        System.out.println("Pokrenuta metoda GET /students IDEMOOOOO");
        return "";
    }

    @POST
    @Path("/students/add")
    public void addStudent(@Param("student") String student){
        System.out.println("Pokrenuta metoda POST /add sa parametrom student: " + student);
    }

    @GET
    @Path("/students/find")
    public int findStudent(@Param("student") String student){
        System.out.println("Pokrenuta metoda GET /find sa parametrom student: " + student);
        return 0;
    }

    @GET
    @Path("/students/jmbg")
    public int studentsJMBG(@Param("student") String student,@Param("jmbg") String jmbg){
        System.out.println("Pokrenuta metoda GET /students/jmbg sa parametrima student: " + student + ", i jmbg: " + jmbg);
        return 0;
    }
}
