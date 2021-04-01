package com.example.greentechlives;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MountainInfo extends AppCompatActivity implements View.OnClickListener{

    Button btnClimateAction, btnUN, btnOurPlanet;
    private final static String ClimateChange_URL = "https://www.undp.org/content/undp/en/home/sustainable-development-goals/goal-13-climate-action.html";
    private final static String SDG_URL = "https://www.undp.org/content/undp/en/home/sustainable-development-goals.html";
    private final static String OurPlanet_URL = "https://www.ourplanet.com/en/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_info);

        btnClimateAction = findViewById(R.id.btnClimateAction);
        btnUN = findViewById(R.id.btnUN);
        btnOurPlanet = findViewById(R.id.btnOurPlanet);

        btnClimateAction.setOnClickListener(this);
        btnUN.setOnClickListener(this);
        btnOurPlanet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()) {
            case R.id.btnClimateAction:
                intent.setData(Uri.parse(ClimateChange_URL));
                startActivity(intent);
                break;
            case R.id.btnUN:
                intent.setData(Uri.parse(SDG_URL));
                startActivity(intent);
                break;
            case R.id.btnOurPlanet:
                intent.setData(Uri.parse(OurPlanet_URL));
                startActivity(intent);
                break;

        }

    }
}