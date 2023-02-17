package com.example.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    private FirebaseAuth mAuth;
    private EditText etemail,etPassword;
    private TextView tvForgot,tvNewAcc;
    private Button btnDoneLogin;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachComponents();
        btnDoneLogin.setOnClickListener(view1 -> {
            check();

        });
        tvNewAcc.setOnClickListener(view12 -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new SignUp());
            ft.commit();
        });
        tvForgot.setOnClickListener(view13 -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new Forgotpassword());
            ft.commit();
            Toast.makeText(getContext(), "OH OOop", Toast.LENGTH_SHORT).show();

        });

    }
    public void check(){
        String email,password;
        email=etemail.getText().toString().trim();
        password=etPassword.getText().toString().trim();
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(getContext(), "some are empty", Toast.LENGTH_SHORT).show();
            etemail.requestFocus();
            etPassword.requestFocus();
            return;
        }
        if(!isEmailValid(email)){
            Toast.makeText(getContext(), "wrong email pattern!", Toast.LENGTH_SHORT).show();
            etemail.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), task -> {
            if (task.isSuccessful()){
                Toast.makeText(getContext(), "Logged in", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new HomePage());
                ft.commit();
            }
            else {
                Toast.makeText(getContext(), "failed to login ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void attachComponents(){
        mAuth = FirebaseAuth.getInstance();
        etemail = getView().findViewById(R.id.emaillogin);
        etPassword = getView().findViewById(R.id.etPassLogin);
        tvForgot= getView().findViewById(R.id.tvForgot);
        tvNewAcc = getView().findViewById(R.id.tvNewAcc);
        btnDoneLogin = getView().findViewById(R.id.btnDoneLogin);
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}