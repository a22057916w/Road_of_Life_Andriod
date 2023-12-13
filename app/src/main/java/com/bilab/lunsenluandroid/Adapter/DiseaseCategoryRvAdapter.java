package com.bilab.lunsenluandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
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

public class DiseaseCategoryRvAdapter extends RecyclerView.Adapter<DiseaseCategoryRvAdapter.ViewHolder>{
    private final Context context;
    private final ArrayList<DiseaseCategoryModel> diseaseCategoryModelList;

    // Constructor
    public DiseaseCategoryRvAdapter(Context context, ArrayList<DiseaseCategoryModel> diseaseCategoryModelList) {
        this.context = context;
        this.diseaseCategoryModelList = diseaseCategoryModelList;
    }

    @NonNull
    @Override
    public DiseaseCategoryRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_disease_regular, parent, false);

        return new DiseaseCategoryRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseCategoryRvAdapter.ViewHolder holder, int position) {
        DiseaseCategoryModel model = diseaseCategoryModelList.get(position);
        holder.tv_disease_name.setText((model.getDiseaseName()));
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imv_disease_icon = itemView.findViewById(R.id.imv_disease_icon_regular);
            tv_disease_name = itemView.findViewById(R.id.tv_disease_name_regular);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String category = getDiseaseCategory().first;
                    int icon = getDiseaseCategory().second;

                    Intent openDiseaseSelectionIntent = new Intent(view.getContext(), DiseaseSelectionActivity.class);
                    openDiseaseSelectionIntent.putExtra(Constant.EXTRA_STARTER_ACTIVITY_NAME, view.getContext().getClass().getName());
                    openDiseaseSelectionIntent.putExtra(Constant.EXTRA_DISEASE_CATEGORY, category);
                    openDiseaseSelectionIntent.putExtra(Constant.EXTRA_DISEASE_ICON, icon);
                    view.getContext().startActivity(openDiseaseSelectionIntent);
                }
            });
        }

        private Pair<String, Integer> getDiseaseCategory() {
            String disease_category = tv_disease_name.getText().toString();

            if(disease_category.contains("子宮"))
                return new Pair<>(Constant.UTERUS, R.drawable.ic_uterus);
            if(disease_category.contains("卵巢"))
                return new Pair<>(Constant.OVARY, R.drawable.ic_ovary);
            if(disease_category.contains("膀胱"))
                return new Pair<>(Constant.BLADDER, R.drawable.ic_bladder);
            if(disease_category.contains("大腸"))
                return new Pair<>(Constant.RECTUM, R.drawable.ic_rectum);

            return null;
        }
    }
}
