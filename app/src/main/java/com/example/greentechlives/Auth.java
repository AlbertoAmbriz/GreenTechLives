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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Auth extends AppCompatActivity {

    private EditText medtextName;
    private EditText medtextEmail;
    private EditText medtextPassword;
    private Button mbtnRegister;
    private Button mbtnLogIn;

    private String name = "";
    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        medtextName = (EditText)findViewById(R.id.edtxtName);
        medtextEmail = (EditText)findViewById(R.id.edtxtEmail);
        medtextPassword = (EditText)findViewById(R.id.edtxtPassword);

        mbtnRegister = (Button)findViewById(R.id.btnRegister);
        mbtnLogIn = (Button)findViewById(R.id.btnLogIn);

        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = medtextName.getText().toString();
                email = medtextEmail.getText().toString();
                password = medtextPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    if (password.length() >= 6) {
                        registerUser();

                    }
                    else {
                        Toast.makeText(Auth.this, "The password must have at least 6 characters", Toast.LENGTH_LONG).show();
                    }

                    registerUser();
                }
                else {
                    Toast.makeText(Auth.this, "You must complete all", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("Name", name);
                    map.put("Email", email);
                    map.put("Password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("User").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(Auth.this, LonInToHome.class));
                                finish();
                            }
                            else {
                                Toast.makeText(Auth.this, "Something went wrong", Toast.LENGTH_LONG).show();

                            }

                        }
                    });

                }

                else {
                    Toast.makeText(Auth.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}