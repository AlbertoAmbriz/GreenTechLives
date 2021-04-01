package com.example.greentechlives;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    private Button mLogIn;
    private Button mPleaseRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mLogIn = (Button)findViewById(R.id.btnLogIn);
        mPleaseRegister = (Button)findViewById(R.id.btnPleaseRegister);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LonInToHome.class);
                startActivity(i);
            }
        });

        mPleaseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), Auth.class);
                startActivity(i);
            }
        });

    }

}