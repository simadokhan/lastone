package com.example.test;

import android.util.Log;
import android.widget.Toast;

public class BestScores {
   private int BestScore ;
   private String eMAIL ;

    public BestScores(int bestScore, String eMAIL) {
       this.BestScore = bestScore;
        this.eMAIL = eMAIL;
    }

    public BestScores(){
    }

    public int getBestScore() {
        return BestScore;
    }

    public void setBestScore(int bestScore) {

        BestScore = bestScore;
    }

    public String geteMAIL() {
        return eMAIL;
    }

    public void seteMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }
}
