package org.example.services;

import org.example.annotations.Autowired;
import org.example.annotations.Bean;
import org.example.repositories.StudentRepository;

@Bean
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar instance servis dependency-ja!");
        return this.studentRepository.allStudents();
    }
}
