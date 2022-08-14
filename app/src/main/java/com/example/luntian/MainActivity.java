package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView humidityBtn, soilmoistureBtn, plannerBtn, plantcyclopediaBtn, plantgrowthBtn, reminderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        humidityBtn = findViewById(R.id.humidityBtn);
        soilmoistureBtn = findViewById(R.id.soilmoistureBtn);
        plannerBtn = findViewById(R.id.plannerBtn);
        plantcyclopediaBtn = findViewById(R.id.plancyclopediaBtn);
        plantgrowthBtn = findViewById(R.id.plantgrowthBtn);
        reminderBtn = findViewById(R.id.reminderBtn);




        humidityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, humidity_monitoring.class);
                startActivity(intent);

            }
        });

        soilmoistureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, soil_moisture.class);
                startActivity(intent);

            }
        });

        plantcyclopediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, plantcyclopedia.class);
                startActivity(intent);

            }
        });

        plantgrowthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, plant_growth_tracking.class);
                startActivity(intent);

            }
        });

        reminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);

            }
        });

        /*
       */

    }

}