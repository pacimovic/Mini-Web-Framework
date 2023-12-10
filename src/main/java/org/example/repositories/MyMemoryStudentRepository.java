package org.example.repositories;

import org.example.annotations.Qualifier;
import org.example.annotations.Service;

@Qualifier("memory")
@Service
public class MyMemoryStudentRepository implements StudentRepository{
    @Override
    public String allStudents() {
        System.out.println("Pokrenuta metoda unutar Memory repository-ja");
        return "";
    }
}
