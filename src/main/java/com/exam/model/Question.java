package com.exam.model;

public class Question {
    private int id;
    private String text;
    private String opA, opB, opC, opD;
    private String correctAns;

    public Question() {}

    public Question(int id, String text, String opA, String opB, String opC, String opD, String correctAns) {
        this.id = id;
        this.text = text;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.correctAns = correctAns;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getOpA() { return opA; }
    public void setOpA(String opA) { this.opA = opA; }
    public String getOpB() { return opB; }
    public void setOpB(String opB) { this.opB = opB; }
    public String getOpC() { return opC; }
    public void setOpC(String opC) { this.opC = opC; }
    public String getOpD() { return opD; }
    public void setOpD(String opD) { this.opD = opD; }
    public String getCorrectAns() { return correctAns; }
    public void setCorrectAns(String correctAns) { this.correctAns = correctAns; }
}