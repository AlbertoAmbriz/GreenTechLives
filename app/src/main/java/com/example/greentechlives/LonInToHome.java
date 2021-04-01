package com.example.greentechlives;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LonInToHome extends AppCompatActivity {

    private EditText mUserEmail;
    private EditText mUserPassword;
    private Button mLogInToHome;

    private String userEmail = "";
    private String userPassword = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lon_in_to_home);

        mAuth = FirebaseAuth.getInstance();

        mUserEmail = (EditText)findViewById(R.id.edtxtUserEmail);
        mUserPassword = (EditText)findViewById(R.id.edtxtUserPassword);
        mLogInToHome = (Button)findViewById(R.id.btnLogInToHome);

        mLogInToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = mUserEmail.getText().toString();
                userPassword = mUserPassword.getText().toString();

                if (!userEmail.isEmpty() && !userPassword.isEmpty()) {
                    loginUser();

                }
                else {
                    Toast.makeText(LonInToHome.this, "Complete all the spaces", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LonInToHome.this, MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LonInToHome.this, "The email or password is incorrect", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LonInToHome.this, MainActivity.class));
            finish();
        }
    }

}