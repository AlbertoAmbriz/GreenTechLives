package com.example.greentechlives;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MountainTips extends AppCompatActivity {

    TextView tip1, tip2, tip3, tip4, tip5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_tips);

        tip1 = findViewById(R.id.textTip1);
        tip2 = findViewById(R.id.textTip2);
        tip3 = findViewById(R.id.textTip3);
        tip4 = findViewById(R.id.textTip4);
        tip5 = findViewById(R.id.textTip5);

    }

//    public void cards extends text
//    String tipUno = "Si usas tu dispositivo por mucho tiempo es recomendable bajar el brillo de la pantalla";
//    tip1
//


}