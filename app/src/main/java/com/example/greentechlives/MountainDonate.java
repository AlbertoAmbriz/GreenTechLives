package com.example.greentechlives;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MountainDonate extends AppCompatActivity implements View.OnClickListener{

    Button btnDonate;
    private final static String Donate_URL = "https://give.undp.org/campaign/undp-giving/c120717?utm_source=newweb&utm_medium=WEB&utm_campaign=CENTRAL&c_src=CENTRAL&c_src2=WEB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_donate);

        btnDonate = findViewById(R.id.btnDonate);

        btnDonate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()) {
            case R.id.btnDonate:
                intent.setData(Uri.parse(Donate_URL));
                startActivity(intent);
                break;

        }
    }
}