package com.example.test;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyholderView extends RecyclerView.ViewHolder {
public TextView Score,User;
    public MyholderView(@NonNull View itemView) {
        super(itemView);
        Score= itemView.findViewById(R.id.Score);
        User =itemView.findViewById(R.id.Mode);
    }
}
