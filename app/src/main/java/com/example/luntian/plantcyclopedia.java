package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luntian.adapter.plantAdapter;
import com.example.luntian.model.PlantModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class plantcyclopedia extends AppCompatActivity {

    TextView homeTitle;
    Button addPlantInfoBtn;
    EditText searchView;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseStorage storage;
    RecyclerView plantListView;
    plantAdapter plantadapter;
    List<PlantModel> plantModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantcyclopedia);

        plantListView = (RecyclerView) findViewById(R.id.plantListview);
        plantListView.setLayoutManager(new LinearLayoutManager(plantcyclopedia.this));

        FirebaseRecyclerOptions<PlantModel> options =
                new FirebaseRecyclerOptions.Builder<PlantModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Plants"), PlantModel.class)
                        .build();
        plantadapter = new plantAdapter(options);
        plantListView.setAdapter(plantadapter);
        plantadapter.notifyDataSetChanged();

        searchView = findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        homeTitle = findViewById(R.id.appTitle);
        homeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plantcyclopedia.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addPlantInfoBtn = findViewById(R.id.addPlantInfoBtn);
        addPlantInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plantcyclopedia.this, add_plant_info.class);
                startActivity(intent);
            }
        });

    }

    private void filter(String textSearch) {
        //String upperFirst = textSearch.substring(0,1).toUpperCase() + textSearch.substring(1).toLowerCase(); textSearch + "\uf8ff"
        FirebaseRecyclerOptions<PlantModel> options =
                new FirebaseRecyclerOptions.Builder<PlantModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Plants").orderByChild("PlantName").startAt(textSearch)
                                .endAt(textSearch + "\uf8ff"), PlantModel.class)
                        .build();
        plantadapter = new plantAdapter(options);
        plantadapter.startListening();
        plantListView.setAdapter(plantadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        plantadapter.startListening();
        plantadapter.notifyDataSetChanged();
    }
    @Override
    protected void onStop() {
        super.onStop();
        plantadapter.stopListening();

    }
}