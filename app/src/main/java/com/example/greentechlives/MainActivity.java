package com.example.greentechlives;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    BroadcastReceiver batteryBroadcast;
    IntentFilter intentFilter;
    Button btnInfo, btnGoDonate, btnTips;
    TextView level, voltage, batteryRemaining, batteryCapacity, currentNow, carbonFoot, energyRemaining;


    OpenHelperGreenTechLives ayuda = new OpenHelperGreenTechLives(this, "_DATABASE", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        level = findViewById(R.id.textLevel);
        voltage = findViewById(R.id.textVoltage);
        batteryRemaining = findViewById(R.id.textBatteryRemaining);
        batteryCapacity = findViewById(R.id.textBatteryCapacity);
        currentNow = findViewById(R.id.textCurrentNow);
        carbonFoot = findViewById(R.id.textCarbonFoot);
//        energyRemaining = findViewById(R.id.textEnergyRemaining);

        btnInfo = (Button)findViewById(R.id.btnInfo);
        btnGoDonate = (Button)findViewById(R.id.btnGoDonate);
        btnTips = (Button)findViewById(R.id.btnTips);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MountainInfo.class);
                startActivity(i);

            }
        });

        btnGoDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MountainDonate.class);
                startActivity(i);

            }
        });

        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MountainTips.class);
                startActivity(i);

            }
        });


        intentFilterAndBroadcast();

    }


    private void intentFilterAndBroadcast() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        batteryBroadcast = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                    level.setText(String.valueOf(intent.getIntExtra("level", 0)));

                    float voltTemp = (float) (intent.getIntExtra("voltage", 0) * 0.001);
                    voltage.setText(voltTemp + " V");


                    BatteryManager mBatteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);

                    // BATTERY CAPACITY IN MICRO AMPERE-HOUR
                    float batteryRemainingTemp = mBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
                    batteryRemaining.setText(batteryRemainingTemp + " mAh");

                    // REMAINING BATTERY CAPACITY AS A INTEGER PERCENTAGE OF TOTAL CAPACITY
                    Integer remainCapacity = mBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                    batteryCapacity.setText(remainCapacity + " %");

                    // INSTANTANEOUS BATTERY CURRENT IN MICRO AMPERE
                    float currentNowTemp = mBatteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
                    currentNow.setText(currentNowTemp + " ampere");

                    // CARBON FOOT PRINT CALCULATION
                    double carbonFootTemp = ((batteryRemainingTemp - ((remainCapacity  * batteryRemainingTemp) / 100)) * (voltTemp) * (0.000000001)) * 0.0004999;
                    carbonFoot.setText("Has producido " + carbonFootTemp + " gramos de CO2");

                    ayuda._open();
                    ayuda.appendData(Integer.valueOf(String.valueOf(level.getText())), Float.valueOf((float) carbonFootTemp));
                    //   ayuda.appenDataDos(Integer.valueOf(String.valueOf(level.getText())), Float.valueOf((float) carbonFootTemp));
                    ayuda._close();


                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(batteryBroadcast,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(batteryBroadcast);
    }
}