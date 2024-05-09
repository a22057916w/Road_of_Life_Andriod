package com.bilab.lunsenluandroid.main.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.conf.Person;

import java.util.ArrayList;

public class DiseaseHomeAdapter extends RecyclerView.Adapter<DiseaseHomeAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<DiseaseHomeModel> diseaseHomeModelArrayList;

    // Constructor
    public DiseaseHomeAdapter(Context context, ArrayList<DiseaseHomeModel> diseaseHomeModelArrayList) {
        this.context = context;
        this.diseaseHomeModelArrayList = diseaseHomeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_disease_primary, parent, false);

//                Intent myIntent = new Intent(this.context, WebViewActivity.class); //WebViewActivityName is the  activity name of the webview activity
//        Intent openDiseaseChartIntent = new Intent(this.context, DiseaseChartActivity.class);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myIntent.putExtra("url", "https://www.google.com.tw/"); //Add your url in "yourUrlHere"
//                view.getContext().startActivity(myIntent);
//            view.getContext().startActivity(openDiseaseChartIntent);

            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseHomeAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        DiseaseHomeModel model = diseaseHomeModelArrayList.get(position);
        holder.tv_diseaseName.setText(model.getDiseaseName());
        holder.tv_diseaseDescription.setText(model.getDiseaseDescription());
        holder.imv_diseaseIcon.setImageResource(model.getDiseaseImage());
        holder.tv_diseaseRisk.setText(model.getDiseaseRisk());

        Double pRisk = Person.getInstance().getRisk(model.getType());
        if (pRisk <= 50.0D)
            holder.tv_diseaseRisk.setTextColor(Color.GREEN);
        else if (pRisk <= 75.0D)
            holder.tv_diseaseRisk.setTextColor(Color.parseColor("#FFBD33"));
        else
            holder.tv_diseaseRisk.setTextColor(Color.RED);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openDiseaseChartIntent = new Intent(view.getContext(), DiseaseChartActivity.class);
                openDiseaseChartIntent.putExtra(Constant.EXTRA_DISEASE_CATEGORY, model.getType());
                view.getContext().startActivity(openDiseaseChartIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return diseaseHomeModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imv_diseaseIcon;
        private final TextView tv_diseaseName;
        private final TextView tv_diseaseDescription;
        private final TextView tv_diseaseRisk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imv_diseaseIcon = itemView.findViewById(R.id.imv_disease_icon_primary);
            tv_diseaseName = itemView.findViewById(R.id.tv_disease_name_primary);
            tv_diseaseDescription = itemView.findViewById(R.id.tv_disease_info_primary);
            tv_diseaseRisk = itemView.findViewById((R.id.tv_disease_risk_primary));
        }
    }
}

