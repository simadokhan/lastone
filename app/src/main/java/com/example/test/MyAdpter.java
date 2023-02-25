package com.example.test;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class MyAdpter extends RecyclerView.Adapter<MyholderView> {
    private  Context context ;
    private ArrayList<BestScores> Scores;
    @NonNull
    @Override
    public MyholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyholderView(LayoutInflater.from(context).inflate(R.layout.careerrecyclerview,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyholderView holder, int position) {
     holder.Score.setText(Scores.get(position).getBestScore());
     holder.User.setText(Scores.get(position).geteMAIL());
    }
    @Override
    public int getItemCount() {
        return Scores.size();
    }
    public MyAdpter(Context context, ArrayList<BestScores> scores) {
        this.context = context;
        Scores = scores;
    }
}
