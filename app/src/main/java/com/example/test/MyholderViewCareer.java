package com.example.test;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyholderViewCareer extends RecyclerView.ViewHolder {
public TextView Score,Mode,Date;
    public MyholderViewCareer(@NonNull View itemView) {
        super(itemView);
        Score= itemView.findViewById(R.id.Score);
        Mode =itemView.findViewById(R.id.Mode);
        Date=itemView.findViewById(R.id.date);
    }
}
