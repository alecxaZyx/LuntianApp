package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener */{

    ImageView humidityBtn, soilmoistureBtn, plannerBtn, plantcyclopediaBtn, plantgrowthBtn, reminderBtn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(intent);

                        overridePendingTransition(0,0);

                        return true;

                    case R.id.planttracking:
                        Intent intent2 = new Intent(getApplicationContext(), plant_growth_tracking.class);

                        startActivity(intent2);

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.plantcyclopedia:
                        Intent intent3 = new Intent(getApplicationContext(), plantcyclopedia.class);

                        startActivity(intent3);

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.planner:
                        Intent intent4 = new Intent(getApplicationContext(), ReminderMainActivity.class);

                        startActivity(intent4);

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.settings:
                        Intent intent5 = new Intent(getApplicationContext(), Settings.class);

                        startActivity(intent5);

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

       /* drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);*/

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
        plannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReminderMainActivity.class);
                startActivity(intent);
            }
        });







        /*----------------------------------TOOLBAR------------------------------------------*/

        /*  Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/

        /*---------Navigation Drawer Menu -----------------------------------------
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);*/



    }
/*
    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_humidity:
                Intent intent = new Intent(MainActivity.this,humidity_monitoring.class);
                startActivity(intent);
                break;
            case R.id.nav_soil:

                intent = new Intent(MainActivity.this, soil_moisture.class);
                startActivity(intent);
                break;
            case R.id.nav_plant:

                intent = new Intent(MainActivity.this, plant_growth_tracking.class);
                startActivity(intent);
                break;
            case R.id.nav_plantcyclopedia:

                intent = new Intent(MainActivity.this, plantcyclopedia.class);
                startActivity(intent);
                break;
            case R.id.nav_reminders:

                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_notification:

                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_planner:

                intent = new Intent(MainActivity.this, ReminderMainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:

                intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START)  ;
        return true;
    }
*/

    }


