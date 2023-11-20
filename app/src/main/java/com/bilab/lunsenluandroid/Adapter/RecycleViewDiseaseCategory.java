package com.bilab.lunsenluandroid.Adapter;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.DiseaseCategoryModel;

import java.util.ArrayList;

public class RecycleViewDiseaseCategory extends RecyclerView.Adapter<RecycleViewDiseaseCategory.ViewHolder>{
    private final Context context;
    private final ArrayList<DiseaseCategoryModel> diseaseCategoryModelList;

    // Constructor
    public RecycleViewDiseaseCategory(Context context, ArrayList<DiseaseCategoryModel> diseaseCategoryModelList) {
        this.context = context;
        this.diseaseCategoryModelList = diseaseCategoryModelList;
    }

    @NonNull
    @Override
    public RecycleViewDiseaseCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_disease_primary, parent, false);
        return new RecycleViewDiseaseCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewDiseaseCategory.ViewHolder holder, int position) {
        DiseaseCategoryModel model = diseaseCategoryModelList.get(position);
        holder.tv_diseaseName.setText((model.getDiseaseName()));
        holder.tv_diseaseAmount.setText((model.getDiseaseAmount()));
        holder.tv_diseaseRisk.setVisibility(View.INVISIBLE);
        holder.imv_diseaseIcon.setImageResource(model.getDiseaseImage());
    }

    @Override
    public int getItemCount() {
        return diseaseCategoryModelList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imv_diseaseIcon;
        private final TextView tv_diseaseName;
        private final TextView tv_diseaseAmount;
        private final TextView tv_diseaseRisk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_diseaseIcon = itemView.findViewById(R.id.imv_diseaseIcon);
            tv_diseaseName = itemView.findViewById(R.id.tv_diseaseName);
            tv_diseaseAmount = itemView.findViewById(R.id.tv_diseaseInfo);
            tv_diseaseRisk = itemView.findViewById(R.id.tv_diseaseRisk);
        }
    }
}
