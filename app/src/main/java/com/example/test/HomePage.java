package com.example.test;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {
    private FirebaseAuth mAuth;
    Button help,playHARD,playMID,playEASY,Carrer;
    protected FireBaseServices db;
    protected TextView bestscore;
    MediaPlayer mediaPlayer;



//    MediaPlayer player=MediaPlayer.create(this,) the music thing in the classroom

    //MediaPlayer mysong;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onStart() {
        super.onStart();
    //    Startmusic();
        connect();
     db.getFire().collection("SCOREHARD")
                 .get().toString();

        help.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new helprel());
            ft.commit();
        });

        playHARD.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Playhard());
            ft.commit();
        });
        playMID.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Playmid());
            ft.commit();
        });
        playEASY.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new PlaySinglePlayer());
            ft.commit();
        });
        Carrer.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Settings());
            ft.commit();

        });
//        db.getFire().collection("SCOREHARD").get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                    if (!queryDocumentSnapshots.isEmpty()){
//                        List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
//                        for (DocumentSnapshot d : list)
//                            Score p = d.
//                    }
//                });
//       bestscore.setText("best score :"+ db.getFire().collection("SCOREHARD")
//               .get().toString());
                }


    private void connect() {
        mAuth = FirebaseAuth.getInstance();
        help=getView().findViewById(R.id.help);
        playMID=getView().findViewById(R.id.playMID);
        playHARD=getView().findViewById(R.id.playHARD);
        playEASY=getView().findViewById(R.id.playES);
        Carrer=getView().findViewById(R.id.carrer1);
        db= FireBaseServices.getinstance();
        bestscore=getView().findViewById(R.id.bestscore);
       // mediaPlayer = MediaPlayer.create(this.getContext(),R.raw.music1);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.the3points,menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }
//    public void Startmusic(){
//        mediaPlayer.start();
//    }
}
