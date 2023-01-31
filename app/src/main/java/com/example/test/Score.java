package com.example.test;
import java.time.LocalDate;
import java.util.Date;

public class Score {
    private int scoreHARD;
    private String date;
    private int scoreMID;
    private int scoreES;


    public Score(int scoreHARD, String date, int scoreMID, int scoreES) {
        this.scoreHARD = scoreHARD;
        this.date = date;
        this.scoreMID = scoreMID;
        this.scoreES = scoreES;
    }

    public int getScoreHARD() {
        return scoreHARD;
    }


    public int getScoreMID() {
        return scoreMID;
    }

    public int getScoreES() {
        return scoreES;
    }

    public void setScoreHARD(int scoreHARD) {
        this.scoreHARD = scoreHARD;
    }

    public void setScoreMID(int scoreMID) {
        this.scoreMID = scoreMID;
    }

    public void setScoreES(int scoreES) {
        this.scoreES = scoreES;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
