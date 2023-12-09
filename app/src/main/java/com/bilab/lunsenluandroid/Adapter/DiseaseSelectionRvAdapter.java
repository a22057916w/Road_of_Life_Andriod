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
import com.bilab.lunsenluandroid.model.DiseaseSelectionModel;

import java.util.ArrayList;

public class DiseaseSelectionRvAdapter extends RecyclerView.Adapter<DiseaseSelectionRvAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList;

    public DiseaseSelectionRvAdapter(Context context, ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList) {
        this.context = context;
        this.diseaseSelectionModelArrayList = diseaseSelectionModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_disease_selection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseSelectionRvAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        DiseaseSelectionModel model = diseaseSelectionModelArrayList.get(position);
        holder.diseaseName.setText(model.getDiseaseName());
        holder.diseaseImage.setImageResource(model.getDiseaseImage());

        //holder.checkBox.setChecked();
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return diseaseSelectionModelArrayList.size();
    }

    // View holder class for initializing of views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final TextView diseaseName;
        private final ImageView diseaseImage;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.chb_disease_selection);
            diseaseName = itemView.findViewById(R.id.tv_disease_name_selection);
            diseaseImage = itemView.findViewById(R.id.imv_disease_icon_selection);
        }
    }
}
