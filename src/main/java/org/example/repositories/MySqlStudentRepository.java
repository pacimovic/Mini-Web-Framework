package org.example.repositories;

import org.example.annotations.Qualifier;
import org.example.annotations.Service;
import org.example.models.Student;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("sql")
public class MySqlStudentRepository implements StudentRepository{

    public List<Student> allStudents(){
        System.out.println("Pokrenuta metoda unutar SQL repository-ja");
        Student s1 = new Student(1, "Petar", "321321432");
        Student s2 = new Student(2, "Jelena", "4534543");
        Student s3 = new Student(3, "Milos", "9879798");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        return students;
    }
}
