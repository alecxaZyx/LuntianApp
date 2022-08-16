package com.example.luntian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ReminderMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    String tomorrow;
    Calendar calendar = Calendar.getInstance();
    String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()).toString();
    com.example.luntian.adapter.ListAdapter ListAdapter;
    ArrayList<Reminder> list;
    TextView homeTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_main);


        Date date;
        calendar.add(Calendar.DATE, 1);
        date = calendar.getTime();
        Format format = new SimpleDateFormat("dd-MM-yyyy");
        tomorrow = format.format(date);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("Reminder");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Reminder>();
        ListAdapter = new ListAdapter(this,list);
        recyclerView.setAdapter(ListAdapter);

        database.orderByChild("dt").startAt(currentdate).endAt(tomorrow).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Reminder reminder = dataSnapshot.getValue(Reminder.class);
                        list.add(reminder);


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