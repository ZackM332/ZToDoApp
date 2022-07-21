package com.example.todolistapptest;

public class Task {

    public long id;
    public String name;

    public Task(){
        id = -1;
        name = "UNASSIGNED!";
    }

    public Task(String name){
        id = -1;
        this.name = name;
    }

    public Task(String name, int id){
        this.id = id;
        this.name = name;
    }
}
