package com.example.mycampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.Provider;

public class LoginActivity extends AppCompatActivity {
    EditText Email, Password;
    Button Login;
    TextView sign_up;
    FirebaseAuth Fauth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Login = findViewById(R.id.Login);
        sign_up = findViewById(R.id.SignUp);


        // Initialize Firebase Auth
        Fauth = FirebaseAuth.getInstance();

        // Check if the user is signed in
        super.onStart();
        FirebaseUser currentUser = Fauth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }

        //Log in activity
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserEmail = Email.getText().toString().trim();
                String UserPassword = Password.getText().toString().trim();

                //Checking if fields are empty
                if (TextUtils.isEmpty(UserEmail)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(UserPassword)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Login activity using email and password
                Fauth.startActivityForSignInWithProvider(this, new Build()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


            }
        });

    }

}
