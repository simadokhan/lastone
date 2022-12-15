package com.example.test;

import static com.example.test.Login.isEmailValid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Forgotpassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Forgotpassword extends Fragment {
private EditText email ;
private Button  a7a ;
private TextView back ;
private FirebaseAuth mAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Forgotpassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Forgotpassword.
     */
    // TODO: Rename and change types and number of parameters
    public static Forgotpassword newInstance(String param1, String param2) {
        Forgotpassword fragment = new Forgotpassword();
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
        return inflater.inflate(R.layout.fragment_forgotpassword, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        email=getActivity().findViewById(R.id.enteremail);
        a7a=getActivity().findViewById(R.id.passforgot);
        back =getActivity().findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();
        a7a.setOnClickListener(view -> {
            if (!isEmailValid(email.getText().toString().trim())){
                email.requestFocus();
                email.setError("email is wrong ");
                return;
            }
            mAuth.sendPasswordResetEmail(email.getText().toString());
        });
        back.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Login());
            ft.commit();
        });
    }
}