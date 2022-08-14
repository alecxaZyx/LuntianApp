package com.example.luntian;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity  {

    TextView timer, date;
    int timerHour,timerMinute;
    private TextView selectDate;
    EditText title, desc;
    Button confirm, planner;
    DatabaseReference reminderDBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //time
        timer = findViewById(R.id.timer);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity3.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                timerHour = hour;
                                timerMinute = minute;
                                //store
                                String time = timerHour + ":" + timerMinute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    // 12 hour time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    // set selected time
                                    timer.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous time
                timePickerDialog.updateTime(timerHour, timerMinute);
                //show dialog
                timePickerDialog.show();
            }
        });
        //calendar
        selectDate = findViewById(R.id.date);
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity3.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        selectDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        //button
        Button button = (Button) findViewById(R.id.planner);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, ReminderMainActivity.class);
                startActivity(intent);
            }
        });

        //database
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        timer = findViewById(R.id.timer);
        date = findViewById(R.id.date);
        confirm = findViewById(R.id.confirm);

        reminderDBRef = FirebaseDatabase.getInstance().getReference().child("Reminder");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertReminderData();
            }
        });
    }

    private void insertReminderData() {
        String t = title.getText().toString();
        String d = desc.getText().toString();
        String tm = timer.getText().toString();
        String dt = date.getText().toString();
        Reminder reminder = new Reminder(t,d,tm,dt);

        reminderDBRef.push().setValue(reminder);
        Toast.makeText(MainActivity3.this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }

}
