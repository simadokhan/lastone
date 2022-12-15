package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new HomePage());
        ft.commit();
    }
//    BallGenerator bg = new BallGenerator();
//        System.out.println(bg.());
//    Map<String, Integer> new_ball = bg.diffrentBallGenerator("Hard");
//        System.out.println(new_ball.get("red"));
//        System.out.println(new_ball.get("blue"));
//        System.out.println("==========");
}