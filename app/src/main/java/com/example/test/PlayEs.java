package com.example.test;

import static android.content.ContentValues.TAG;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayEs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayEs extends Fragment {
private ImageView circle1 , circle2 , circle3 , circle4 , exit;
private int i=from0to3();
private TextView timer,Score;
private final Date date1= Calendar.getInstance().getTime() ;
private final String dateestring = DateFormat.getInstance().format(date1);
private final long starttimeinmillis=60000;
private AlertDialog.Builder builder;
private long mtimeleft=starttimeinmillis;
private FireBaseServices db;
private String userEmail;
FirebaseAuth  mAuth = FirebaseAuth.getInstance();
private Score score;
private boolean mtimerrunning;

    // TODO: define present correct ImageView holder
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayEs() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayEs.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayEs newInstance(String param1, String param2) {
        PlayEs fragment = new PlayEs();
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
        return inflater.inflate(R.layout.fragment_playeasy, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           userEmail= currentUser.getEmail();
        }
        score=new Score(dateestring,0,"easy",userEmail);
        connect();
        game();
        StartTimer();
        exit.setOnClickListener(view1 -> {
            builder.setTitle("EXIT").setMessage("ARE U SURE").setCancelable(true).setPositiveButton("ok", (dialogInterface, i) -> {
                addingData();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new HomePage());
                ft.commit();
            }).show();
        });
    }
    public void connect() {
        circle1=getView().findViewById(R.id.circle1E);
        circle2=getView().findViewById(R.id.circle2E);
        circle3=getView().findViewById(R.id.circle3E);
        circle4=getView().findViewById(R.id.circle4E);
        exit=getView().findViewById(R.id.exitE);
        builder=new AlertDialog.Builder(getContext());
        timer=getView().findViewById(R.id.timerE);
        Score=getView().findViewById(R.id.HARDSCORE);
        db= FireBaseServices.getinstance();
    }
    private int from0to3 () {
        Random random = new Random();
        int i = random.nextInt(3);
        return i ;
    }
    public void game(){
        ImageView[] CIRCLE ={circle1,circle2,circle3,circle4};
        BallGenerator rgb= new BallGenerator();
        for (int g=0; g<CIRCLE.length;g++){
            if(g!=i){
                CIRCLE[g].setColorFilter(Color.rgb(rgb.getR_value(), rgb.getG_value(), rgb.getB_value()));
            }
            if (score.getScore()<= 500 ){

                CIRCLE[i].setColorFilter(Color.rgb(rgb.esr(), rgb.esg(), rgb.esb()));}
            else if (score.getScore()>500 && score.getScore()<2000 ){
                CIRCLE[i].setColorFilter(Color.rgb(rgb.esr(), rgb.getG_value(),rgb.esb()));
              //  fragment.getView().setBackgroundColor(Color.RED);
            }
            else  {
                CIRCLE[i].setColorFilter(Color.rgb(rgb.esr(), rgb.getG_value(),rgb.getB_value()));
            }
        }
        circle1.setOnClickListener(view12 -> isRight(0));
        circle2.setOnClickListener(view12 -> isRight(1));
        circle3.setOnClickListener(view12 -> isRight(2));
        circle4.setOnClickListener(view12 -> isRight(3));
    }

    @SuppressLint("SetTextI18n")
    private void isRight(int x) {
        if (i==x){
            i=from0to3();
            Score.setText("score: "+Scoreset());
            game();
        }else if (score.getScore()<500 ){

            addingData();
            builder.setTitle("bad luck").setMessage("I knew you didn't have it in you TwT  ").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        }
        else if (score.getScore()>500 && score.getScore()<2000 ){

            addingData();

            builder.setTitle("BAD LUCK").setMessage("wrong answer baby").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        }
        else if (score.getScore()>2000 && score.getScore()<4000 ){

            addingData();

            builder.setTitle("BAD LUCK").setMessage("HAHA I knew you couldn't make it XD ").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        }
        else {


            addingData();
            builder.setTitle("BAD LUCK").setMessage("HAHA it's hard isn't it  ?ToT").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        }


        }

   private void StartTimer(){
       CountDownTimer mcountdowntimer = new CountDownTimer(mtimeleft, 1000) {
           @Override
           public void onTick(long millisuntilFinished) {
               mtimeleft = millisuntilFinished;
               UpdateCountsowntext();
           }

           @Override
           public void onFinish() {

           }
       }.start();

   }
   private void  UpdateCountsowntext(){
        int minutes = (int) (mtimeleft/1000)/60;
        int sec=(int)(mtimeleft/1000)%60;
        String timeLeftFormatted= String.format(Locale.getDefault(),"%02d:%02d",minutes,sec);
         timer.setText(timeLeftFormatted);
}
    private int Scoreset(){
        if (mtimeleft>= 55000){
            score.setScore(score.getScore()+50);
        }
        else if(mtimeleft> 50000){
            score.setScore(score.getScore()+40);

        }
        else if(mtimeleft>= 45000){
            score.setScore(score.getScore()+30);

        }
        else if(mtimeleft>= 35000){
            score.setScore(score.getScore()+20);
        }
        else {
            score.setScore(score.getScore()+10);

        }
        return score.getScore();
    }

    public void addingData(){
        db.getFire().collection("SCORE")
                .add(score)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

}

