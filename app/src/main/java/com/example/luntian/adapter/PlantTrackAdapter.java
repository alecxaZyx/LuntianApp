package com.example.luntian.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luntian.R;
import com.example.luntian.model.TrackModel;
import com.example.luntian.plant_trackview;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PlantTrackAdapter extends FirebaseRecyclerAdapter<TrackModel, PlantTrackAdapter.mViewHolder> {

    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    public PlantTrackAdapter(@NonNull FirebaseRecyclerOptions<TrackModel> tracklist) {
        super(tracklist);
    }


    @Override
    protected void onBindViewHolder(@NonNull PlantTrackAdapter.mViewHolder holder, int position, @NonNull TrackModel trackModel) {

        holder.plantName.setText(trackModel.getPlantName());
        holder.plantDate.setText(trackModel.getDatePlanted());
        Picasso.get().load(trackModel.getImage()).into(holder.trackImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, plant_trackview.class);
                String pName = trackModel.getPlantName();
                String pDate = trackModel.getDatePlanted();
                String pImg = trackModel.getImage();
                String pID = trackModel.getId();



                intent.putExtra("PlantName", pName);
                intent.putExtra("DatePlanted", pDate);
                intent.putExtra("Image", pImg);
                intent.putExtra("id", pID);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public PlantTrackAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantcard_layout, parent, false);
        return new mViewHolder(v);
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        ImageView trackImg;
        TextView plantName;
        TextView plantDate;
        public mViewHolder(@NonNull View itemView){
            super(itemView);
            trackImg = itemView.findViewById(R.id.cardPlant_img);
            plantName = itemView.findViewById(R.id.cardPlant_name);
            plantDate = itemView.findViewById(R.id.cardPlant_scientific);
        }
    }
}
