package com.exam.model;

public class QuestionInsight {
    private int questionId;
    private String questionText;
    private int totalAttempts;
    private int wrongAttempts;
    private double missPercentage;

    public QuestionInsight(int questionId, String questionText, int totalAttempts, int wrongAttempts) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.totalAttempts = totalAttempts;
        this.wrongAttempts = wrongAttempts;
        this.missPercentage = totalAttempts > 0 ? (wrongAttempts * 100.0 / totalAttempts) : 0;
    }

    public int getQuestionId() { return questionId; }
    public String getQuestionText() { return questionText; }
    public int getTotalAttempts() { return totalAttempts; }
    public int getWrongAttempts() { return wrongAttempts; }
    public double getMissPercentage() { return missPercentage; }
}