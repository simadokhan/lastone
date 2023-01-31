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
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Playhard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Playhard extends Fragment {
    protected ImageView circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9, exit;
    protected int i = from0to8();
    private final Date date1=Calendar.getInstance().getTime() ;
    private final String dateestring = DateFormat.getInstance().format(date1);
    protected Score score = new Score(0,dateestring, 0, 0);
    protected BackgroundColorSpan backgroundColorSpan;
    protected TextView timer, SCORE;
    protected final long starttimeinmillis = 60000;
    protected CountDownTimer mcountdowntimer;
    protected long mtimeleft = starttimeinmillis;
    protected AlertDialog.Builder builder;
    protected boolean mtimerrunning;
    protected FireBaseServices db;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Playhard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Playhard.
     */
    // TODO: Rename and change types and number of parameters
    public static Playhard newInstance(String param1, String param2) {
        Playhard fragment = new Playhard();
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
        return inflater.inflate(R.layout.fragment_playhard, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        connect();
        mcountdowntimer = new CountDownTimer(mtimeleft, 100) {
            @Override
            public void onTick(long millisuntilFinished) {
                mtimeleft = millisuntilFinished;
                UpdateCountsowntext();
            }

            @Override
            public void onFinish() {
                mtimerrunning = false;
            }
        }.start();
        gameH();
        exit.setOnClickListener(view1 -> {

            db.getFire().collection("SCOREHARD")
                    .add(score)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

            builder.setTitle("EXIT").setMessage("ARE U SURE").setCancelable(true).setPositiveButton("yup", (dialogInterface, i) -> {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new HomePage());
                ft.commit();
            }).show();

        });


    }

    public void connect() {
        circle1 = getView().findViewById(R.id.circle1h);
        circle2 = getView().findViewById(R.id.circle2h);
        circle3 = getView().findViewById(R.id.circle3h);
        circle4 = getView().findViewById(R.id.circle4h);
        circle5 = getView().findViewById(R.id.circle5h);
        circle6 = getView().findViewById(R.id.circle6h);
        circle7 = getView().findViewById(R.id.circle7h);
        circle8 = getView().findViewById(R.id.circle8h);
        circle9 = getView().findViewById(R.id.circle9h);
        SCORE = getView().findViewById(R.id.HARDSCORE);
        exit = getView().findViewById(R.id.exit);
        timer = getView().findViewById(R.id.timer);
        builder = new AlertDialog.Builder(getContext());
        db = FireBaseServices.getinstance();

    }

    private int from0to8() {
        Random random = new Random();
        int i = random.nextInt(8);
        return i;
    }

    private int from0to3() {
        Random random = new Random();
        int i = random.nextInt(3);
        return i;
    }
    public void gameH() {
        ImageView[] CIRCLE1 = {circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9};
        BallGenerator rgb = new BallGenerator();
        for (int g = 0; g < CIRCLE1.length; g++) {
            if (g != i) {
                CIRCLE1[g].setColorFilter(Color.rgb(rgb.getR_value(), rgb.getG_value(), rgb.getB_value()));
            }
        }
        if (score.getScoreHARD() <= 500) {
            CIRCLE1[i].setColorFilter(Color.rgb(rgb.Hardr(), rgb.Hardg(), rgb.Hardb()));
        } else if (score.getScoreHARD() > 500 && score.getScoreHARD() < 2000) {
            CIRCLE1[i].setColorFilter(Color.rgb(rgb.Hardr(), rgb.Hardg(), rgb.getB_value()));
        } else {
            CIRCLE1[i].setColorFilter(Color.rgb(rgb.Hardr(), rgb.getG_value(), rgb.getB_value()));
        }
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
        circle5.setOnClickListener(view12 -> {
            isRight(4);
        });

        circle6.setOnClickListener(view12 -> {
            isRight(5);

        });
        circle7.setOnClickListener(view12 -> {
            isRight(6);

        });
        circle8.setOnClickListener(view12 -> {
            isRight(7);

        });
        circle9.setOnClickListener(view12 -> {
            isRight(8);

        });

    }

    @SuppressLint("SetTextI18n")
    private void isRight(int x) {
        if (i == x) {
            i = from0to8();
            SCORE.setText("score: " + Scoreset());
            gameH();
            resetTime();

        } else if (score.getScoreHARD() < 150) {

            db.getFire().collection("SCOREHARD")
                    .add(score)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            builder.setTitle("bad luck").setMessage("I knew you didn't have it in you TwT  ").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();

        } else if (score.getScoreHARD() > 150 && score.getScoreHARD() < 600) {

            db.getFire().collection("SCOREHARD")
                    .add(score)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
            builder.setTitle("BAD LUCK").setMessage("wrong answer baby").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        } else if (score.getScoreHARD() > 600 && score.getScoreHARD() < 1500) {

            db.getFire().collection("SCOREHARD")
                    .add(score)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
            builder.setTitle("BAD LUCK").setMessage("HAHA I knew you couldn't make it XD ").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        } else {

            db.getFire().collection("SCOREHARD")
                    .add(score)
                    .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));

            builder.setTitle("BAD LUCK").setMessage("HAHA it's hard isn't it  ?ToT").show();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        }
    }

    private void StartTimer() {
        mcountdowntimer = new CountDownTimer(starttimeinmillis, 1000) {
            @Override
            public void onTick(long millisuntilFinished) {
                mtimeleft = millisuntilFinished;
                UpdateCountsowntext();
            }

            @Override
            public void onFinish() {
                mtimerrunning = false;
            }
        }.start();
        mtimerrunning = true;
    }

    private void UpdateCountsowntext() {
        int minutes = (int) (mtimeleft / 1000) / 60;
        int sec = (int) (mtimeleft / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, sec);
        timer.setText(timeLeftFormatted);
    }

    private void resetTime() {
        mtimeleft = starttimeinmillis;
        mcountdowntimer.cancel();
        StartTimer();
        UpdateCountsowntext();

    }

    private int Scoreset() {
        if (mtimeleft >= 55000) {
            score.setScoreHARD(score.getScoreHARD() + 50);
        } else if (mtimeleft > 50000) {
            score.setScoreHARD(score.getScoreHARD() + 40);
        } else if (mtimeleft >= 45000) {
            score.setScoreHARD(score.getScoreHARD() + 30);
        } else if (mtimeleft >= 35000) {
            score.setScoreHARD(score.getScoreHARD() + 20);
        } else {
            score.setScoreHARD(score.getScoreHARD() + 10);
        }
        return score.getScoreHARD();
    }
}
