package com.example.test;
import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Career#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Career extends Fragment implements AdapterView.OnItemSelectedListener {
private ImageView BACKhome;
private FirebaseAuth mAuth;
private String userEmail;
private FirebaseFirestore db = FirebaseFirestore.getInstance();
private Spinner spinner;
private Button sginout;
private ProgressBar loadingPB;
private RecyclerView CreerCR;
private MyAdpterCareer ScoreCR;
 private CallBackScores Call;


private ArrayList<Score> Scores = new ArrayList<>();
private ArrayList<Score> ALL_play = new ArrayList<>();
private ArrayList<Score> Hard_play = new ArrayList<>();
private ArrayList<Score> ES_play = new ArrayList<>();
private ArrayList<Score> Mid_play = new ArrayList<>();
    private AlertDialog.Builder builder;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Career() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Career.
     */
    // TODO: Rename and change types and number of parameters
    public static Career newInstance(String param1, String param2) {
        Career fragment = new Career();
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
        return inflater.inflate(R.layout.fragment_career, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        connect();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            userEmail= currentUser.getEmail();
        }
        Call=Scores->GetTheBestScoreOfTheUser();
        db.collection("SCORE")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult())
                            Scores.add(document.toObject(Score.class));
                    }
                    Call.onCallBack(Scores);
                    CreerCR.setHasFixedSize(true);
                    CreerCR.setLayoutManager(new LinearLayoutManager(getContext()));

                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "no data || something wrong ", Toast.LENGTH_SHORT).show();
                });
        BACKhome.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        });
        sginout.setOnClickListener(view -> {
            builder.setTitle("signOUT").setMessage("ARE U SURE").setCancelable(true).setPositiveButton("yup", (dialogInterface, i) -> {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new Login());
                ft.commit();
            }).show();
        });
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.All, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TheModes();
        if (spinner.getSelectedItem().toString().equals("Hard")){
           ScoreCR=new MyAdpterCareer(getContext(),Hard_play);
            CreerCR.setAdapter(ScoreCR);
        }else if (spinner.getSelectedItem().toString().equals("Medium")){
           ScoreCR=new MyAdpterCareer(getContext(),Mid_play);
            CreerCR.setAdapter(ScoreCR);
        }else if (spinner.getSelectedItem().toString().equals("Easy")){
           ScoreCR=new MyAdpterCareer(getContext(),ES_play);
            CreerCR.setAdapter(ScoreCR);
        }else {
            ScoreCR=new MyAdpterCareer(getContext(),ALL_play);
            CreerCR.setAdapter(ScoreCR);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        ScoreCR=new MyAdpterCareer(getContext(),ALL_play);
        CreerCR.setAdapter(ScoreCR);
    }
    public  void  connect(){
        BACKhome=getView().findViewById(R.id.backhome);
        builder=new AlertDialog.Builder(getContext());
        mAuth = FirebaseAuth.getInstance();
        spinner=getView().findViewById(R.id.spinner);
        loadingPB = getView().findViewById(R.id.idProgressBar);
        sginout=getActivity().findViewById(R.id.signOUT);
        CreerCR=getView().findViewById(R.id.recyclerViewCarer);
    }
    public void  GetTheBestScoreOfTheUser(){
        if(!Scores.isEmpty()){
        for (int i = 0 ; i< Scores.size();i++){
            Log.d(TAG, "GetTheBestScoreOfTheUser: "+userEmail+Scores.get(i).getEmail());
            if (Scores.get(i).getEmail()!=null&&Scores.get(i).getEmail().equals(userEmail)){
                Log.d(TAG, "nj7t  "+i);
                ALL_play.add(Scores.get(i));
            }

        }

        }else {
            Toast.makeText(getContext(), "u did not play ur first game ", Toast.LENGTH_SHORT).show();
        }
        loadingPB.setVisibility(View.GONE);
    }
    public void TheModes(){
        for (int i = 0 ; i < ALL_play.size();i++){
            if(ALL_play.get(i).getMoDE().equals("Hard"))
            {
                Hard_play.add(ALL_play.get(i));
            }else  if(ALL_play.get(i).getMoDE().equals("Mid"))
            {
                Mid_play.add(ALL_play.get(i));
            }else  if(ALL_play.get(i).getMoDE().equals("easy"))
            {
                ES_play.add(ALL_play.get(i));
            }

        }
    }

}
