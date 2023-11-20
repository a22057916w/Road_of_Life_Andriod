package com.bilab.lunsenluandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.DiseaseSelectionActivity;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.model.DiseaseCategoryModel;
import com.bilab.lunsenluandroid.util.Constant;

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
        holder.tv_disease_name.setText((model.getDiseaseName()));
        holder.tv_disease_amount.setText((model.getDiseaseAmount()));
        holder.tv_disease_risk.setVisibility(View.INVISIBLE);
        holder.imv_disease_icon.setImageResource(model.getDiseaseImage());
    }

    @Override
    public int getItemCount() {
        return diseaseCategoryModelList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imv_disease_icon;
        private final TextView tv_disease_name;
        private final TextView tv_disease_amount;
        private final TextView tv_disease_risk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imv_disease_icon = itemView.findViewById(R.id.imv_disease_icon);
            tv_disease_name = itemView.findViewById(R.id.tv_disease_name);
            tv_disease_amount = itemView.findViewById(R.id.tv_disease_info);
            tv_disease_risk = itemView.findViewById(R.id.tv_disease_risk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openDiseaseSelectionIntent = new Intent(view.getContext(), DiseaseSelectionActivity.class);
                    openDiseaseSelectionIntent.putExtra(Constant.EXTRA_DISEASE_CATEGORY, getDiseaseCategory());
                    view.getContext().startActivity(openDiseaseSelectionIntent);
                }
            });
        }

        private String getDiseaseCategory() {
            String disease_category = tv_disease_name.getText().toString();

            if(disease_category.contains("子宮"))
                return Constant.UTERUS;
            if(disease_category.contains("卵巢"))
                return Constant.OVARY;
            if(disease_category.contains("膀胱"))
                return Constant.BLADDER;
            if(disease_category.contains("大腸"))
                return Constant.RECTUM;

            return Constant.NONE;
        }
    }
}
