package com.bilab.lunsenluandroid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilab.lunsenluandroid.R;
import com.github.mikephil.charting.components.LegendEntry;

import java.util.List;

public class LegendAdapter extends RecyclerView.Adapter<LegendAdapter.LegendViewHolder> {

    private List<LegendEntry> legendEntries;

    public LegendAdapter(List<LegendEntry> legendEntries) {
        this.legendEntries = legendEntries;
    }

    @NonNull
    @Override
    public LegendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_legend, parent, false);
        return new LegendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LegendViewHolder holder, int position) {
        LegendEntry legendEntry = legendEntries.get(position);

        // Set the legend icon, label, and other properties
        holder.legendIcon.setBackgroundColor(legendEntry.formColor);
        holder.legendLabel.setText(legendEntry.label);
    }

    @Override
    public int getItemCount() {
        return legendEntries.size();
    }

    public static class LegendViewHolder extends RecyclerView.ViewHolder {
        ImageView legendIcon;
        TextView legendLabel;

        public LegendViewHolder(@NonNull View itemView) {
            super(itemView);
            legendIcon = itemView.findViewById(R.id.imv_legend_icon);
            legendLabel = itemView.findViewById(R.id.tv_legend_label);
        }
    }
}
