package com.example.luntian;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_plant_track extends AppCompatActivity {

    Spinner spnWF1, spnWF2, spnFF1, spnFF2;
    EditText datePlanted, trackName;
    ImageView trackplantImg;
    Button trackPlantBtn;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    private static final int img_code=1;
    public Uri imageUri=null;
    ProgressDialog pd;

    String pName;

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseStorage storage;
    StorageReference storageReference;

    Intent rIn;

    public add_plant_track() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_track);
        /* back button declaration */
        ImageView backBtn = findViewById(R.id.plantbackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(add_plant_track.this, plant_growth_tracking.class);
                startActivity(intent);
                finish();
            }
        });
        /* spinner declaration */
        spnWF1 = findViewById(R.id.spnWF1);
        spnWF2 = findViewById(R.id.spnWF2);
        spnFF1 = findViewById(R.id.spnFF1);
        spnFF2 = findViewById(R.id.spnFF2);

        String[] spn1 =new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] spn2 = new String[]{"day", "week", "month"};

        ArrayAdapter<String> adapter1 =new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spn1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWF1.setAdapter(adapter1);
        spnFF1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spn2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWF2.setAdapter(adapter2);
        spnFF2.setAdapter(adapter2);


        trackName = findViewById(R.id.trackName);
        trackplantImg = findViewById(R.id.planttrackImg);

        //if track plant is clicked on a specific plantcyclopedia gets intent then transfer here








        /* get current date for date planted */
        datePlanted = findViewById(R.id.trackDatePlanted);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        datePlanted.setText(date);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
                datePlanted.setText(myFormat.format(calendar.getTime()));
            }
        };
        datePlanted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_plant_track.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("TrackPlant");
        storage = FirebaseStorage.getInstance();
        String refId = mReference.push().getKey();

        pd = new ProgressDialog(this);





        rIn = getIntent();
        if (rIn != null){
            String newtrackName = rIn.getStringExtra("AddNewTrack");
            //String newTrackImg = rIn.getStringExtra("AddNewImg");
            trackName.setText(newtrackName);
            //Picasso.get().load(newTrackImg).into(trackplantImg);
            pName = trackName.getText().toString().trim();

            //String newTrackImg = rIn.getStringExtra("AddNewImg");
            //imageUri = Uri.parse(newTrackImg);
           // trackplantImg.setImageURI(imageUri);
        }
        trackplantImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, img_code);
            }
        });
        trackPlantBtn = findViewById(R.id.trackPlantBtn);
        trackPlantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                pName = trackName.getText().toString().trim();
                String pPlanted = datePlanted.getText().toString().trim();
                String spinnerWF1 = spnWF1.getSelectedItem().toString().trim();
                String spinnerWF2 = spnWF2.getSelectedItem().toString().trim();
                String spinnerFF1 = spnFF1.getSelectedItem().toString().trim();
                String spinnerFF2 = spnFF2.getSelectedItem().toString().trim();

                pd.setTitle("Adding plant...");
                pd.show();

                storageReference = storage.getReference().child("TrackImage").child(imageUri.getLastPathSegment());
                storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String t = task.getResult().toString();

                                DatabaseReference newPost = mReference.push();
                                newPost.child("ID").setValue(refId);
                                newPost.child("Image").setValue(task.getResult().toString());
                                newPost.child("PlantName").setValue(pName);
                                newPost.child("DatePlanted").setValue(pPlanted);
                                newPost.child("WaterFrequency1").setValue(spinnerWF1);
                                newPost.child("WaterFrequency2").setValue(spinnerWF2);
                                newPost.child("FertilizerFreq1").setValue(spinnerFF1);
                                newPost.child("FertilizerFreq2").setValue(spinnerFF2);
                                pd.dismiss();

                                Intent intent = new Intent(add_plant_track.this, plant_growth_tracking.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if(rIn != null){
            String newTrackImg = rIn.getStringExtra("AddNewImg");
            imageUri = Uri.parse(newTrackImg);
            trackplantImg.setImageURI(imageUri);
        } else*/ if(requestCode == img_code && resultCode == RESULT_OK){
            imageUri = data.getData();
            trackplantImg.setImageURI(imageUri);
        }
    }
}