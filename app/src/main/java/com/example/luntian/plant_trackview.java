package com.example.luntian;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luntian.adapter.RemarkAdapter;
import com.example.luntian.model.GrowthModel;
import com.example.luntian.model.RemarkModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class plant_trackview extends AppCompatActivity {

    ImageView backPlantBtn;
    TextView trackName, trackDate, refId;
    ImageView plantImg;
    String imgUri;
    Button addGrowthBtn, addHeightBtn, addRemarkLayout, addRemarks;
    EditText day, height;
    View addLayout, remarkLayout;

    EditText remarkDate, remarkTxt;

    GraphView graphView;
    LineGraphSeries series;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    RecyclerView remarkListview;
    RemarkAdapter remarkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_trackview);

        plantImg = findViewById(R.id.trackImg);
        trackName = findViewById(R.id.trackPlantName);
        trackDate = findViewById(R.id.trackDatePlanted);
        refId = findViewById(R.id.referenceID);

        Intent rInt = getIntent();
        String name = rInt.getStringExtra("PlantName");
        String date = rInt.getStringExtra("DatePlanted");
        String id = rInt.getStringExtra("id");
        imgUri = rInt.getStringExtra("Image");

        Picasso.get().load(imgUri).into(plantImg);
        trackName.setText(name);
        trackDate.setText(date);
        refId.setText(id);

        backPlantBtn = findViewById(R.id.plantbackBtn);
        backPlantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(plant_trackview.this, plant_growth_tracking.class);
                startActivity(intent);
                finish();
            }
        });

        addGrowthBtn = findViewById(R.id.addGrowthBtn);
        addHeightBtn = findViewById(R.id.addHeightBtn);
        addLayout = findViewById(R.id.addGrowthLayout);

        remarkDate = findViewById(R.id.remarkDate);
        remarkTxt = findViewById(R.id.remarkTxt);

        addGrowthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLayout.setVisibility(View.VISIBLE);
                addGrowthBtn.setVisibility(View.GONE);
            }
        });

        //establish graphView
        day = findViewById(R.id.graphDay);
        height = findViewById(R.id.graphHeight);
        graphView = findViewById(R.id.heightGraphview);
        series = new LineGraphSeries();
        graphView.addSeries(series);
        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Day");
        gridLabel.setVerticalAxisTitle("Height");
        //set graphview viewports
        graphView.getViewport().setMinX(1);
        graphView.getViewport().setMaxX(30);
        graphView.getViewport().setMinY(2.0);
        graphView.getViewport().setMaxY(30.0);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setXAxisBoundsManual(true);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("PlantGrowthRate").child(id);
        setListeners();


        //add remarks view
        addRemarkLayout = findViewById(R.id.addRemarkLayout);
        remarkLayout = findViewById(R.id.addRemarksLayout);
        addRemarks = findViewById(R.id.addRemarkBtn);

        addRemarkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remarkLayout.setVisibility(View.VISIBLE);
                addRemarkLayout.setVisibility(View.GONE);
            }
        });
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        remarkDate.setText(date);
        DatePickerDialog.OnDateSetListener currentDate =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
                remarkDate.setText(myFormat.format(calendar.getTime()));
            }
        };
        remarkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(plant_trackview.this,currentDate,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        addRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String date = remarkDate.getText().toString().trim();
                String remark = remarkTxt.getText().toString().trim();

                mDatabase = FirebaseDatabase.getInstance();
                mReference = mDatabase.getReference("TrackPlantRemarks").child(id);

                DatabaseReference newPost = mReference.push();
                newPost.child("RemarkDate").setValue(date);
                newPost.child("RemarkTxt").setValue(remark);


                remarkLayout.setVisibility(View.GONE);
                addRemarkLayout.setVisibility(View.VISIBLE);
            }
        });

        remarkListview = findViewById(R.id.remarksView);
        remarkListview.setLayoutManager(new LinearLayoutManager(plant_trackview.this));
        FirebaseRecyclerOptions<RemarkModel> remarklist =
                new FirebaseRecyclerOptions.Builder<RemarkModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("TrackPlantRemarks").child(id), RemarkModel.class)
                        .build();
        remarkAdapter = new RemarkAdapter(remarklist);
        remarkAdapter.startListening();
        remarkListview.setAdapter(remarkAdapter);
    }


    private void setListeners() {
        addHeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rID = mReference.push().getKey();
                float dayValueX = Float.parseFloat(day.getText().toString());
                float heightValueY = Float.parseFloat(height.getText().toString());

                GrowthModel growthModel = new GrowthModel(dayValueX, heightValueY);
                mReference.child(rID).setValue(growthModel);


                addLayout.setVisibility(View.GONE);
                addGrowthBtn.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        remarkAdapter.startListening();
        remarkAdapter.notifyDataSetChanged();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for(DataSnapshot mDataSnapshot : snapshot.getChildren())
                {
                    GrowthModel growthModel = mDataSnapshot.getValue(GrowthModel.class);
                    dp[index] = new DataPoint(growthModel.getDay(), growthModel.getHeight());
                    index++;
                }
                series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        remarkAdapter.stopListening();

    }
}

