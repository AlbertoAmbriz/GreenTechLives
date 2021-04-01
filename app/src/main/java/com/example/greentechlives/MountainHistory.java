package com.example.greentechlives;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MountainHistory extends AppCompatActivity {

    private static String TAG = "MountainHistory";

    private LineChart mChart;

    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference mDatabase;
    DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_history);

        mAuth = FirebaseAuth.getInstance();

        mChart = (LineChart)findViewById(R.id.linechart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("reporte")
                .whereEqualTo("uid", mAuth.getUid())
                .orderBy("Date", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Entry> yValues = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("GreenTechLives", document.getId() + " => " + document.getData());
                                Float date = (float)document.getDate("Date").getTime();
                                Float co2 = Float.valueOf(document.getString("CO2_producido"));
                                Log.d("GreenTechLives", date.toString());
                                Log.d("GreenTechLives", co2.toString());
                                yValues.add(new Entry(date, co2));
                            }

                            LineDataSet set1 = new LineDataSet(yValues, "CO2 produced");

                            set1.setFillAlpha(110);
                            set1.setColor(Color.RED);

                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(set1);

                            LineData data = new LineData(dataSets);

                            mChart.setData(data);

                        } else {
                            Log.d("GreenTechLives", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}