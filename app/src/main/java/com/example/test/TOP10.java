package com.example.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TOP10#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TOP10 extends Fragment {
    private FirebaseAuth mAuth;
    private ImageView Backe;
    private ArrayList<BestScores> BestScores = new ArrayList<>();
    private String userEmail;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressBar loadingPB;
    private RecyclerView CareerRV ,CreerCR;
    private MyAdpter ScoreRVAdapter;
    private MyAdpterCareer ScoreCR;
    private CallBack callBack;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TOP10() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TOP10.
     */
    // TODO: Rename and change types and number of parameters
    public static TOP10 newInstance(String param1, String param2) {
        TOP10 fragment = new TOP10();
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
        return inflater.inflate(R.layout.fragment_t_o_p10, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connect();
Backe.setOnClickListener(v -> {
    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.frameLayout, new HomePage());
    ft.commit();
});
        callBack=BestScores->TheTop10(BestScores);
        db.collection("bestSCORE")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()){
                            BestScores.add(document.toObject(BestScores.class));
                        }
                    }
                    callBack.onCallBack(BestScores);
                    CareerRV.setHasFixedSize(true);
                    CareerRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    ScoreRVAdapter = new MyAdpter(getContext(),(BestScores));
                    CareerRV.setAdapter(ScoreRVAdapter);
                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "no data || something wrong ", Toast.LENGTH_SHORT).show();
                });
    }
    public void connect (){
 Backe=getView().findViewById(R.id.backhome);
 CareerRV = getView().findViewById(R.id.Top10);
        mAuth = FirebaseAuth.getInstance();
    }
    public void TheTop10(ArrayList<BestScores>s){

        BestScores max ;
        ArrayList<BestScores>MAX=new ArrayList<>();
        while (s.size()>0){
            max=s.get(0);
            for (int i =0; i<s.size();i++){
                if(s.get(i).getBestScore()>max.getBestScore()){
                    max=s.get(i);
                }
            }
            MAX.add(max);
            s.remove(max);
        }
        for (int i = 0 ; i <MAX.size();i++){
            s.add(MAX.get(i));
            s.get(i).seteMAIL(s.get(i).geteMAIL().replace("@gmail.com",""));
        }
    }
}
