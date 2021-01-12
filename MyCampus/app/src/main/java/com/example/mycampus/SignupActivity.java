package com.example.mycampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
   //variable declaration

    TextView Sign_In;
    private EditText inputFullName, inputEmail, inputRegNo, inputConfirmRegNo, inputPassword, inputConfirmPassword;
    private Button buttonSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Getting Firebase Authentication instance





        Sign_In = (TextView) findViewById(R.id.SignIn);
        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}