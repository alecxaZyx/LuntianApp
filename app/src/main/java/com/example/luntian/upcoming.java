package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luntian.adapter.ListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class upcoming extends AppCompatActivity {


    RecyclerView recyclerView;
   // TextView tvdate;
    DatabaseReference database;
    com.example.luntian.adapter.ListAdapter ListAdapter;
    ArrayList<Reminder> list;
    String tomorrow;
    TextView homeTitle;
    Calendar calendar = Calendar.getInstance();
    String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()).toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        homeTitle = findViewById(R.id.luntian);
        homeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(upcoming.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //tvdate = (TextView) findViewById(R.id.tvdate);

        //tomorrow
        Date date;
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        Format format = new SimpleDateFormat("dd-MM-yyyy");
        tomorrow = format.format(date);
        //retrieve data from database
        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Reminder");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Reminder>();
        ListAdapter = new ListAdapter(this, list);
        recyclerView.setAdapter(ListAdapter);

        database.orderByChild("dt").startAt(tomorrow).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                   // String tvdate = dataSnapshot.child("dt").getValue().toString();
                   // tvdate.setText(tvdate);
                    Reminder reminder = dataSnapshot.getValue(Reminder.class);
                    list.add(reminder);

                }
                ListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}