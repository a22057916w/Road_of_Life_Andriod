package com.bilab.lunsenluandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.WebViewActivity;
import com.bilab.lunsenluandroid.model.HomeModel;

import java.util.ArrayList;

public class RecycleViewHome extends RecyclerView.Adapter<RecycleViewHome.ViewHolder> {

    private final Context context;
    private final ArrayList<HomeModel> homeModelArrayList;

    private WebView webView;

    // Constructor
    public RecycleViewHome(Context context, ArrayList<HomeModel> homeModelArrayList) {
        this.context = context;
        this.homeModelArrayList = homeModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        Intent myIntent = new Intent(this.context, WebViewActivity.class); //WebViewActivityName is the  activity name of the webview activity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myIntent.putExtra("url", "https://www.google.com.tw/"); //Add your url in "yourUrlHere"
                view.getContext().startActivity(myIntent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHome.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        HomeModel model = homeModelArrayList.get(position);
        holder.courseNameTV.setText(model.getDisease_name());
        holder.courseRatingTV.setText("" + model.getDisease_description());
        holder.courseIV.setImageResource(model.getDisease_image());
        holder.risk_precentage.setText(model.getRisk_percentage());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return homeModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView courseIV;
        private final TextView courseNameTV;
        private final TextView courseRatingTV;
        private final TextView risk_precentage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourseImage);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseRatingTV = itemView.findViewById(R.id.idTVCourseRating);
            risk_precentage = itemView.findViewById((R.id.idRiskIndex));
        }
    }
}

