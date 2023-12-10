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
import com.bilab.lunsenluandroid.ui.disease.CheckBoxListener;

import java.util.ArrayList;

public class DiseaseSelectionRvAdapter extends RecyclerView.Adapter<DiseaseSelectionRvAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList;
    private CheckBoxListener checkBoxListener;


    public DiseaseSelectionRvAdapter(Context context, ArrayList<DiseaseSelectionModel> diseaseSelectionModelArrayList) {
        this.context = context;
        this.diseaseSelectionModelArrayList = diseaseSelectionModelArrayList;
    }

    // ===================================== ViewHolder =======================================
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
        holder.tvDiseaseName.setText(model.getDiseaseName());
        holder.tvDiseaseImage.setImageResource(model.getDiseaseImage());
        holder.chbDisease.setChecked(model.isSelected());

        // set the custom listener
        holder.setupCheckBoxListener(checkBoxListener, position);
    }

    @Override
    public int getItemCount() {
        return diseaseSelectionModelArrayList.size();
    }

    // View holder class for initializing of views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox chbDisease;
        private final TextView tvDiseaseName;
        private final ImageView tvDiseaseImage;

        public ViewHolder(View itemView) {
            super(itemView);
            chbDisease = itemView.findViewById(R.id.chb_disease_selection);
            tvDiseaseName = itemView.findViewById(R.id.tv_disease_name_selection);
            tvDiseaseImage = itemView.findViewById(R.id.imv_disease_icon_selection);
        }

        public void setupCheckBoxListener(CheckBoxListener checkBoxListener, int position) {
            chbDisease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBoxListener != null)
                        checkBoxListener.onAdapterButtonClick(position);
                }
            });
        }
    }

    // ============================== Getter and Setter ===================================
    public void setCheckBoxListener(CheckBoxListener checkBoxListener) {
        this.checkBoxListener = checkBoxListener;
    }

    public ArrayList<DiseaseSelectionModel> getDiseaseSelectionModelArrayList() {
        return diseaseSelectionModelArrayList;
    }
}
