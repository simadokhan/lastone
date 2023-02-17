package com.example.test;

import static com.example.test.Login.isEmailValid;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp extends Fragment {
    private EditText etemailsignup, etpasswordsignup, etpassword2signup;
    private Button donesignup;
    private TextView gotologin;
    private FirebaseAuth mAuth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp newInstance(String param1, String param2) {
        SignUp fragment = new SignUp();
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        attachComponents();
        donesignup.setOnClickListener(view -> {
            check();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new HomePage());
            ft.commit();
        });
        gotologin.setOnClickListener(view -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Login());
            ft.commit();
        });
    }

    public void attachComponents() {
        mAuth = FirebaseAuth.getInstance();
        etemailsignup = getView().findViewById(R.id.emailsignup);
        etpasswordsignup = getView().findViewById(R.id.etPasssignup);
        etpassword2signup = getView().findViewById(R.id.passwords2);
        gotologin = getView().findViewById(R.id.gotologin);
        donesignup = getView().findViewById(R.id.passforgot);
    }

    public void check() {
        String email, password, password2;
        email = etemailsignup.getText().toString().trim();
        password = etpasswordsignup.getText().toString().trim();
        password2 = etpassword2signup.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            Toast.makeText(getContext(), "some are empty", Toast.LENGTH_SHORT).show();
            etemailsignup.requestFocus();
            etpasswordsignup.requestFocus();
            etpassword2signup.requestFocus();
            return;
        }
        if (!isEmailValid(email)) {
            Toast.makeText(getContext(), "wrong email pattern!", Toast.LENGTH_SHORT).show();
            etemailsignup.requestFocus();
            return;
        }
        if (password.length() < 8) {
            etpasswordsignup.setError("wrong password");
            // Toast.makeText(getContext(), "wrong password", Toast.LENGTH_SHORT).show();
            etpasswordsignup.requestFocus();
            return;
        }
        if (!password.equals(password2)) {
            etpassword2signup.setError("passwords are not the same ");
            etpassword2signup.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            Toast.makeText(getContext(), "Account created.", Toast.LENGTH_SHORT).show();
            if (mAuth.getCurrentUser() != null) {
                mAuth.signOut();
            }
        }).addOnFailureListener(e -> {
                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
    }