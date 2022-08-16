package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button loginBtn, registerBtn;

    EditText email, password;
    TextView forgetBtn;

    ProgressBar pd;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.login_emailTxt);
        password = findViewById(R.id.login_passwordTxt);

        pd = findViewById(R.id.loginProgressBar);

        forgetBtn = findViewById(R.id.forgetBtn);
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, forget_password.class);
                startActivity(intent);
            }
        });

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = email.getText().toString().trim();
                String loginPWord = password.getText().toString().trim();

                if(loginEmail.isEmpty()){
                    email.setError("Please enter email address!");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()){
                    email.setError("Email format invalid!");
                    email.requestFocus();
                    return;
                }
                if(loginPWord.isEmpty()){
                    password.setError("Please enter password!");
                    password.requestFocus();
                    return;
                }
                pd.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(loginEmail, loginPWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(login.this, "Login Failed!", Toast.LENGTH_LONG).show();
                            pd.setVisibility(View.GONE);
                        }
                    }
                });




               /* */
            }
        });

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}