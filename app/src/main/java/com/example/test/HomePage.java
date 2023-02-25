package com.example.test;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {
    private FirebaseAuth mAuth;
    private Button help,playHARD,playMID,playEASY,Carrer;
    private FireBaseServices db;
    private TextView bestscore;
    private MediaPlayer mediaPlayer;
    private BestScores bestScores;
    private String userEmail ;
    private CallBack call;
    private ArrayList<BestScores> BestScores;



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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connect();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            userEmail= currentUser.getEmail();
        }
        call = bestScores -> GetTheBestScoreOfTheUser();
        db.getFire().collection("bestSCORE")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()){
                            BestScores.add(document.toObject(BestScores.class));
                        }
                        call.onCallBack(BestScores);
                    }
                }).addOnFailureListener(e -> Toast.makeText(getContext(), "no data || something wrong ", Toast.LENGTH_SHORT).show());

        help.setOnClickListener(view1-> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new helprel());
            ft.commit();
        });

        playHARD.setOnClickListener(view1 -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Playhard());
            ft.commit();
        });
        playMID.setOnClickListener(view1 -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Playmid());
            ft.commit();
        });
        playEASY.setOnClickListener(view1 -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new PlayEs());
            ft.commit();
        });
        Carrer.setOnClickListener(view1 -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Career());
            ft.commit();

        });
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
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.music1);
        BestScores=new ArrayList<>();


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.the3points,menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.musicON:
                mediaPlayer.start();
            case R.id.musicoff:
                mediaPlayer.stop();
            case R.id.soundoff:
               // mediaPlayer.stop();
            case R.id.soundON:
                //mediaPlayer.start();


        }
        return super.onOptionsItemSelected(item);
    }
    public void  GetTheBestScoreOfTheUser(){
        for (int i = 0 ; i< BestScores.size();i++){
            if (BestScores.get(i).geteMAIL().equals(userEmail)){
                bestScores = BestScores.get(i);
            }
        }
        bestscore.setText("Ur Best Score :)  "+bestScores.getBestScore());
    }
}
