package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BestScores;
import com.example.test.MyholderView;
import com.example.test.R;
import com.example.test.Score;

import java.util.ArrayList;

public class MyAdpterCareer extends RecyclerView.Adapter<MyholderViewCareer>{
    private  Context context ;
    private ArrayList<Score> Scores;
    @NonNull
    @Override
    public MyholderViewCareer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyholderViewCareer(LayoutInflater.from(context).inflate(R.layout.careerrecyclerview21,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyholderViewCareer holder, int position) {
        holder.Mode.setText("the Mode:"+Scores.get(position).getMoDE());
        holder.Date.setText(Scores.get(position).getDate());
        holder.Score.setText("the Score :  "+Scores.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return Scores.size();
    }
    public MyAdpterCareer(Context context, ArrayList<Score> scores) {
        this.context = context;
        Scores = scores;
    }
}
