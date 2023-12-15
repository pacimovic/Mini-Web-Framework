package org.example.controllers;

import org.example.annotations.*;
import org.example.models.Student;
import org.example.services.NewsService;
import org.example.services.StudentService;

import java.util.List;

@Controller
public class StudentController {

    @Autowired(verbose = false)
    private StudentService studentService;

    @Autowired(verbose = false)
    private NewsService newsService;

    @GET
    @Path("/students")
    public List<Student> allStudents(){
        return this.studentService.allStudents();
    }

    @POST
    @Path("/students/add")
    public void addStudent(@Param("student") String student){
        System.out.println("Pokrenuta metoda POST /add sa parametrom student: " + student);
    }

    @GET
    @Path("/students/find")
    public Student findStudent(@Param("student") String student){
        System.out.println("Pokrenuta metoda GET /find sa parametrom student: " + student);
        Student student1 = new Student(1, "Petar", "1512998710000");
        return student1;
    }

    @GET
    @Path("/students/jmbg")
    public int studentsJMBG(@Param("student") String student,@Param("jmbg") String jmbg){
        System.out.println("Pokrenuta metoda GET /students/jmbg sa parametrima student: " + student + ", i jmbg: " + jmbg);
        return 0;
    }
}
