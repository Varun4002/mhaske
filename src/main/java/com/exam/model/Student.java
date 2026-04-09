package com.exam.model;

public class Student {
    private int id;
    private String name;
    private String rollNo;
    private int score;

    public Student() {}

    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.score = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}