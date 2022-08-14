package com.example.luntian.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luntian.R;
import com.example.luntian.model.RemarkModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RemarkAdapter extends FirebaseRecyclerAdapter<RemarkModel, RemarkAdapter.rViewHolder> {

    public RemarkAdapter(@NonNull FirebaseRecyclerOptions<RemarkModel> remarklist) {
        super(remarklist);
    }

    @Override
    protected void onBindViewHolder(@NonNull RemarkAdapter.rViewHolder holder, int position, @NonNull RemarkModel remarkModel) {
        holder.remarkDate.setText(remarkModel.getRemarkDate());
        holder.remarkTxt.setText(remarkModel.getRemarkTxt());
    }

    @NonNull
    @Override
    public RemarkAdapter.rViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remark_layout, parent, false);
        return new rViewHolder (view);
    }

    class rViewHolder extends RecyclerView.ViewHolder{
        TextView remarkDate;
        TextView remarkTxt;
        public rViewHolder(@NonNull View itemView){
            super(itemView);
            remarkDate = itemView.findViewById(R.id.trackingDate);
            remarkTxt = itemView.findViewById(R.id.trackingRemark);
        }
    }
}

























