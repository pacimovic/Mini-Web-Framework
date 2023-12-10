package org.example.services;

import org.example.annotations.Autowired;
import org.example.annotations.Qualifier;
import org.example.annotations.Service;
import org.example.repositories.MySqlStudentRepository;
import org.example.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired(verbose = true)
    @Qualifier("sql")
    private StudentRepository studentRepository;

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar instance servis dependency-ja!");
        return this.studentRepository.allStudents();
    }
}
