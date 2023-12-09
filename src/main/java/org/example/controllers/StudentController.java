package org.example.controllers;

import org.example.annotations.*;
import org.example.services.NewsService;
import org.example.services.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private NewsService newsService;

    @GET
    @Path("/students")
    public String allStudents(){
        return this.studentService.allStudents();
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
