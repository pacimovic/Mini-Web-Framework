package org.example.models;

public class Student {

    private int id;
    private String name;
    private String jmbg;

    public Student(int id, String name, String jmbg) {
        this.id = id;
        this.name = name;
        this.jmbg = jmbg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
