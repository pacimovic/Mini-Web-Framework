package org.example.repositories;

import org.example.annotations.Qualifier;
import org.example.annotations.Service;

@Service
@Qualifier("sqlRepository")
public class StudentRepository {

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar repository-ja");
        return "";
    }
}
