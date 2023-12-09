package org.example.repositories;

import org.example.annotations.Bean;

@Bean(scope = "prototype")
public class StudentRepository {

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar repository-ja");
        return "";
    }
}
