package org.example.services;

import org.example.annotations.Autowired;
import org.example.annotations.Bean;
import org.example.annotations.Component;
import org.example.annotations.Service;
import org.example.repositories.StudentRepository;

@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar instance servis dependency-ja!");
        return this.studentRepository.allStudents();
    }
}
