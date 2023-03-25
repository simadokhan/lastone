package com.example.test;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
public class MyAdpter extends RecyclerView.Adapter<MyholderView> {
    private  Context context ;
    private ArrayList<BestScores> Scores;
    private FirebaseAuth mAuth;
    private String userEmail ;
    @NonNull
    @Override
    public MyholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyholderView(LayoutInflater.from(context).inflate(R.layout.careerrecyclerview,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyholderView holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            userEmail= currentUser.getEmail();
        }

        if ( position == 0 ){
            holder.Score.setText("The Best Score : "+Scores.get(position).getBestScore());
            holder.Score.setTextColor(Color.rgb(207,181,59));
            holder.User.setText("The Best Player  : "+Scores.get(position).geteMAIL());
            holder.User.setTextColor(Color.rgb(207,181,59));
        }
       else  if (position==1){
            holder.Score.setText("The Top 2 score : "+Scores.get(position).getBestScore());
            holder.Score.setTextColor(Color.rgb(192,192,192));
            holder.User.setText("The Top 2 player  : "+Scores.get(position).geteMAIL());
            holder.User.setTextColor(Color.rgb(192,192,192));
        }
        else if (position==2)
        {
            holder.Score.setText("The Top 3 score : "+Scores.get(position).getBestScore());
            holder.Score.setTextColor(Color.rgb(176,141,87));
            holder.User.setText("The Top 3 player  : "+Scores.get(position).geteMAIL());
            holder.User.setTextColor(Color.rgb(176,141,87));
        }
        else   if (Scores.get(position).geteMAIL()== userEmail ){
            holder.Score.setText("The score : "+Scores.get(position).getBestScore());
            holder.Score.setTextColor(Color.rgb(162,241,239));
            holder.User.setText("The Player: "+Scores.get(position).geteMAIL());
            holder.User.setTextColor(Color.rgb(207,181,59));
        }
     else  {holder.Score.setText("The score : "+Scores.get(position).getBestScore());
     holder.User.setText("The Player : "+Scores.get(position).geteMAIL());}
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
