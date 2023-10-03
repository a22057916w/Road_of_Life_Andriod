package com.bilab.lunsenluandroid.Adapter;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.DetailDiseaseModel;

import java.util.ArrayList;

public class RecycleViewDetailDisease extends RecyclerView.Adapter<RecycleViewDetailDisease.ViewHolder> {

    private final Context context;
    private final ArrayList<DetailDiseaseModel> detailDiseaseModelArrayList;

    public RecycleViewDetailDisease(Context context, ArrayList<DetailDiseaseModel> detailDiseaseModelArrayList) {
        this.context = context;
        this.detailDiseaseModelArrayList = detailDiseaseModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_disease_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewDetailDisease.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        DetailDiseaseModel model = detailDiseaseModelArrayList.get(position);
        holder.diseaseName.setText(model.getDisease_name());
        holder.diseaseImage.setImageResource(model.getDisease_image());

        //holder.checkBox.setChecked();
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return detailDiseaseModelArrayList.size();
    }

    // View holder class for initializing of views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final TextView diseaseName;
        private final ImageView diseaseImage;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            diseaseName = itemView.findViewById(R.id.DiseaseName);
            diseaseImage = itemView.findViewById(R.id.DiseaseImage);
        }
    }
}
