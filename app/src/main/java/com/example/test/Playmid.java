package com.example.test;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Playmid#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Playmid extends Fragment {
    private ImageView circle1 , circle2 , circle3 , circle4 , exit;
    private int i=from0to3(),score;
    private TextView timer,Score;
    private final long starttimeinmillis=60000;
    private CountDownTimer mcountdowntimer;
    private AlertDialog.Builder builder;
    private long mtimeleft=starttimeinmillis;
    private boolean mtimerrunning;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Playmid() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Playmid.
     */
    // TODO: Rename and change types and number of parameters
    public static Playmid newInstance(String param1, String param2) {
        Playmid fragment = new Playmid();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playmid, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connect();
        mcountdowntimer=new CountDownTimer(mtimeleft,100) {
            @Override
            public void onTick(long millisuntilFinished) {
                mtimeleft=millisuntilFinished;
                UpdateCountsowntext();
            }
            @Override
            public void onFinish() {
                mtimerrunning=false;
            }
        }.start();
        game();
        exit.setOnClickListener(view1 -> {
            builder.setTitle("EXIT").setMessage("ARE U SURE").setCancelable(true).setPositiveButton("ok", (dialogInterface, i) -> {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new HomePage());
                ft.commit();
            }).show();
        });
    }
    public void connect() {
        circle1=getView().findViewById(R.id.circle1m);
        circle2=getView().findViewById(R.id.circle2m);
        circle3=getView().findViewById(R.id.circle3m);
        circle4=getView().findViewById(R.id.circle4m);
        exit=getView().findViewById(R.id.exitM);
        timer=getView().findViewById(R.id.timerM);
        builder=new AlertDialog.Builder(getContext());
        Score=getView().findViewById(R.id.HARDSCORE);
    }
    private int from0to3 () {
        Random random = new Random();
        int i = random.nextInt(3 - 0 + 0) + 0;
        return i ;
    }
    public void game(){
        ImageView CIRCLE[]={circle1,circle2,circle3,circle4};
        BallGenerator rgb= new BallGenerator();
        for (int g=0; g<CIRCLE.length;g++){
            if(g!=i){
                CIRCLE[g].setColorFilter(Color.rgb(rgb.getR_value(), rgb.getG_value(), rgb.getB_value()));
            }
        }
        CIRCLE[i].setColorFilter(Color.rgb(rgb.midr(), rgb.Hardg(), rgb.midb()));
        circle1.setOnClickListener(view12 -> {
            isRight(0);
        });

        circle2.setOnClickListener(view12 -> {
            isRight(1);

        });
        circle3.setOnClickListener(view12 -> {
            isRight(2);

        });
        circle4.setOnClickListener(view12 -> {
            isRight(3);

        });
    }

    @SuppressLint("SetTextI18n")
    private void isRight(int x) {
        if (i==x){
            i=from0to3();
            Score.setText("score: "+Scoreset());
            game();
            resetTime();

        }else{
            builder.setTitle("bad luck").setMessage("wrong answer baby").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        }
    }
    private void StartTimer(){
        mcountdowntimer=new CountDownTimer(starttimeinmillis,100) {
            @Override
            public void onTick(long millisuntilFinished) {
                mtimeleft=millisuntilFinished;
                UpdateCountsowntext();
            }
            @Override
            public void onFinish() {
                mtimerrunning=false;
            }
        }.start();
        mtimerrunning=true;
    }
    private void  UpdateCountsowntext(){
        int minutes = (int) (mtimeleft/1000)/60;
        int sec=(int)(mtimeleft/1000)%60;
        String timeLeftFormatted= String.format(Locale.getDefault(),"%02d:%02d",minutes,sec);
        timer.setText(timeLeftFormatted);
    }
    private void resetTime(){
        mtimeleft=starttimeinmillis;
        mcountdowntimer.cancel();
        StartTimer();
        UpdateCountsowntext();

    }
    private int Scoreset(){
        if (mtimeleft>= 55000){
            score += 50;
        }
        else if(mtimeleft<55000&&mtimeleft> 50000 ){
            score+=40;
        }
        else if(mtimeleft<=50000&&mtimeleft>= 45000 ){
            score+=30;
        }
        else if(mtimeleft<45000&&mtimeleft>= 35000 ){
            score+=20;
        }
        else {
            score+=10;
        }
        return score;
    }
}