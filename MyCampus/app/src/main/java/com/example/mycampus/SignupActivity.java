package com.example.mycampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    //variable declaration
    TextView Sign_In;
    private EditText inputFullName, inputEmail, inputRegNo, inputConfirmRegNo, inputPassword, inputConfirmPassword;
    private Button buttonSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Getting Firebase Authentication instance
        auth = FirebaseAuth.getInstance();
        Sign_In = (TextView) findViewById(R.id.SignIn);
        buttonSignUp = (Button) findViewById(R.id.SignUp);
        inputFullName = (EditText) findViewById(R.id.FullName);
        inputEmail = (EditText) findViewById(R.id.Email);
        inputRegNo = (EditText) findViewById(R.id.RegNo);
        inputConfirmRegNo = (EditText) findViewById(R.id.ConfirmRegNo);
        inputPassword = (EditText) findViewById(R.id.Password);
        inputConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //Sign up task
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FullName = inputFullName.getText().toString().trim();
                String Email = inputEmail.getText().toString().trim();
                String RegNo = inputRegNo.getText().toString().trim();
                String ConfirmRegNo = inputConfirmRegNo.getText().toString().trim();
                String Password = inputPassword.getText().toString().trim();
                String ConfirmPassword = inputConfirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(FullName)){
                    Toast.makeText(getApplicationContext(), "Enter Your Full Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(getApplicationContext(), "Enter Your Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(RegNo)){
                    Toast.makeText(getApplicationContext(), "Enter Your Registration Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ConfirmRegNo)){
                    Toast.makeText(getApplicationContext(), "Retype Your Registration Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(getApplicationContext(), "Enter Your Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Password.length() < 8){
                    Toast.makeText(getApplicationContext(), "Your Password is too short, Use 8 minimum characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(ConfirmPassword)){
                    Toast.makeText(getApplicationContext(), "Confirm Your Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //Creation of user
                auth.createUserWithEmailAndPassword(RegNo, Password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignupActivity.this, "createUserWithEmail:OnComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user.
                        // If sign in succeeds the auth state listener
                        // will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Authentication Failed." + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });


            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    }