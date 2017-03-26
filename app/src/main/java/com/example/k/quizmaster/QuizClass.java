package com.example.k.quizmaster;

/**
 * Created by k on 3/26/2017.
 */

public class QuizClass {
    int quizNumber;
    String quizName;
    int quizId;

    public QuizClass(int quizNumber, String quizName, int quizId) {
        this.quizNumber = quizNumber;
        this.quizName = quizName;
        this.quizId = quizId;
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
