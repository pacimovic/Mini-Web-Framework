package org.example.repositories;

import org.example.annotations.Bean;
import org.example.annotations.Component;
import org.example.annotations.Service;

@Service
public class StudentRepository {

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar repository-ja");
        return "";
    }
}
