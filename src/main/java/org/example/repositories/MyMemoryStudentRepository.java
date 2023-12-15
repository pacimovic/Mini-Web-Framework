package org.example.repositories;

import org.example.annotations.Qualifier;
import org.example.annotations.Service;
import org.example.models.Student;

import java.util.List;

@Qualifier("memory")
@Service
public class MyMemoryStudentRepository implements StudentRepository{
    @Override
    public List<Student> allStudents() {
        System.out.println("Pokrenuta metoda unutar Memory repository-ja");
        return null;
    }
}
