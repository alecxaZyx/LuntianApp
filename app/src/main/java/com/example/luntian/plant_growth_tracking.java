package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luntian.adapter.PlantTrackAdapter;
import com.example.luntian.model.TrackModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class plant_growth_tracking extends AppCompatActivity {

    Button addPlantTrackBtn, viewTrackView;
    RecyclerView trackListView;
    PlantTrackAdapter trackAdapter;
    TextView homeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_growth_tracking);
        homeTitle = findViewById(R.id.appTitle);
        homeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plant_growth_tracking.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addPlantTrackBtn = findViewById(R.id.addPlantTrackBtn);
        addPlantTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plant_growth_tracking.this, add_plant_track.class);
                startActivity(intent);
                finish();
            }
        });
        trackListView = findViewById(R.id.trackPlantListview);
        trackListView.setLayoutManager(new LinearLayoutManager(plant_growth_tracking.this));
        FirebaseRecyclerOptions<TrackModel> tracklist =
                new FirebaseRecyclerOptions.Builder<TrackModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TrackPlant"), TrackModel.class)
                        .build();
        trackAdapter = new PlantTrackAdapter(tracklist);
        trackListView.setAdapter(trackAdapter);
        trackAdapter.notifyDataSetChanged();



        viewTrackView = findViewById(R.id.viewTrackView);
        viewTrackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plant_growth_tracking.this, plant_trackview.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        trackAdapter.startListening();
        trackAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onStop() {
        super.onStop();
        trackAdapter.stopListening();

    }
}