package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

import java.util.ArrayList;

public class ReminderMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    com.example.luntian.adapter.ListAdapter ListAdapter;
    ArrayList<Reminder> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_main);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Reminder");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Reminder>();
        ListAdapter = new ListAdapter(this,list);
        recyclerView.setAdapter(ListAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String dtDB = (String) dataSnapshot.child("dt").getValue();
                    String cdtDB = (String) dataSnapshot.child("currentDate").getValue();
                    //Date dateFromDB = currentDate.parse(dateAsStringFromDB);
                    //Date dateFromDB = currentDate.parse(dateAsStringFromDB);
                    if (dtDB == cdtDB) {
                        Reminder reminder = dataSnapshot.getValue(Reminder.class);
                        list.add(reminder);
                    }
                }
                ListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //upcoming button
        Button button = (Button) findViewById(R.id.upcoming);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ReminderMainActivity.this, upcoming.class);
                startActivity(intent);
            }
        });

    }

}