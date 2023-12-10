package org.example.repositories;

import org.example.annotations.Qualifier;
import org.example.annotations.Service;

@Service
@Qualifier("sql")
public class MySqlStudentRepository implements StudentRepository{

    public String allStudents(){
        System.out.println("Pokrenuta metoda unutar SQL repository-ja");
        return "";
    }
}
