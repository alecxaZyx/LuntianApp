package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class humidity_monitoring extends AppCompatActivity {

    TextView homeTitle;
    Button setHumidityBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_monitoring);

        homeTitle = findViewById(R.id.appTitle);
        homeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(humidity_monitoring.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        View currentHumidity = findViewById(R.id.currentHumidity);
        View setHumidity = findViewById(R.id.setHumidity);

        setHumidityBtn = findViewById(R.id.setHumidityBtn);

        currentHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHumidity.setVisibility(View.VISIBLE);
                setHumidityBtn.setVisibility(View.VISIBLE);
                currentHumidity.setVisibility(View.INVISIBLE);


            }
        });

        setHumidityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentHumidity.setVisibility(View.VISIBLE);
                setHumidity.setVisibility(View.INVISIBLE);
            }

        });


    }
}