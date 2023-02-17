package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdpter extends RecyclerView.Adapter<MyholderView> {
    Context context ;
    ArrayList<Score> Scores;
    @NonNull
    @Override
    public MyholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyholderView(LayoutInflater.from(context).inflate(R.layout.careerrecyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyholderView holder, int position) {
     holder.Score.setText(Scores.get(position).getScore());
     holder.Mode.setText(Scores.get(position).getMoDE());
    }

    @Override
    public int getItemCount() {
        return Scores.size();
    }

    public MyAdpter(ArrayList<Score> context, Context scores) {
        this.context = scores;
        Scores = context;
    }
}
