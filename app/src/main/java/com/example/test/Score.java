package com.example.test;


public class Score {

    private String date;
    private int score;
    private String moDE;
    private String Email ;

    public Score() {
    }

    public Score(String date, int score, String moDE , String Email) {
        this.date = date;
        this.score = score;
        this.moDE = moDE;
        this.Email=Email;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public void setDate(String date) {this.date = date;}

    public void setScore(int score) {
        this.score = score;
    }

    public String getMoDE() {return moDE;}

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}