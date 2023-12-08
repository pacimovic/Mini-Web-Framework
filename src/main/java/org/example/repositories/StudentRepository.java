package org.example.repositories;

import org.example.annotations.Bean;

@Bean
public class StudentRepository {

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar repository-ja");
        return "";
    }
}
