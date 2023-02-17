package com.example.test;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyholderView extends RecyclerView.ViewHolder {
TextView Score,Mode;
    public MyholderView(@NonNull View itemView) {
        super(itemView);
        Score= itemView.findViewById(R.id.Score1);
        Mode=itemView.findViewById(R.id.Mode1);
    }
}
