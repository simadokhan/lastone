package com.example.test;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
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
private ProgressBar loadingPB;
private RecyclerView CareerRV;
private MyAdpter ScoreRVAdapter;
 private  Score c ;

private ArrayList<Score> Scores = new ArrayList<>();
private ArrayList<String> ALL_play = new ArrayList<>();
private ArrayList<String> Hard_play = new ArrayList<>();
private ArrayList<String> ES_play = new ArrayList<>();
private ArrayList<String> Mid_play = new ArrayList<>();
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
        CareerRV.setHasFixedSize(true);
        CareerRV.setLayoutManager(new LinearLayoutManager(getContext()));
        ScoreRVAdapter = new MyAdpter(Scores, getContext());
        CareerRV.setAdapter(ScoreRVAdapter);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.All, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        BACKhome.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        });
        //ToDo: for (hard , easy , mid ) and connecting it with the spinner + the Top 10 thing
        db.collection("SCORE")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult())
                           Scores.add(document.toObject(Score.class));
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "no data || something wrong ", Toast.LENGTH_SHORT).show();
                });

        // TODO :
        //  adding the data to recycler view.
        CareerRV.setLayoutManager(new LinearLayoutManager(getContext()));
        CareerRV.setAdapter(ScoreRVAdapter);


    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public  void  connect(){
        BACKhome=getView().findViewById(R.id.backhome);
        mAuth = FirebaseAuth.getInstance();
        spinner=getView().findViewById(R.id.spinner);
        loadingPB = getView().findViewById(R.id.idProgressBar);
        CareerRV = getView().findViewById(R.id.recyclerViewCarer);
    }
}
