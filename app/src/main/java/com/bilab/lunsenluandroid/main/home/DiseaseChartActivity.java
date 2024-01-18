package com.bilab.lunsenluandroid.main.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;

import android.util.Log;
import android.widget.TextView;

import com.bilab.lunsenluandroid.conf.Constant;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiseaseChartActivity extends AppCompatActivity {
    private RecyclerView rv_legend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_chart);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent receiverIntent = getIntent();
        if (receiverIntent == null || receiverIntent.getComponent() == null) {
            Log.d("DiseaseChartActivity", "Do not receive any Intent.");
            throw new NullPointerException();
        }

        String cancer = receiverIntent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY);
        TextView tv_chart = findViewById(R.id.tv_chart);
        tv_chart.setText(cancer);


        Person person = Person.getInstance();
        ArrayList<String> diseases = person.getDiseases(cancer);


        HorizontalBarChart horizontalBarChart = findViewById(R.id.horizontalBarChart);

        // Set Y Axis (Left Y-axis)
        YAxis leftYAxis = horizontalBarChart.getAxisLeft();
        leftYAxis.setDrawGridLines(false);

        // Set Y Axis (Right Y-axis)
        YAxis rightYAxis = horizontalBarChart.getAxisRight();
        rightYAxis.setDrawGridLines(false);

        // Set X Axis
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        // Ensure Y-axis starts from 0
        leftYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMinimum(0f);


        // Set horizontal bar data
        List<IBarDataSet> dataSets = new ArrayList<>();
        List<LegendEntry> legendEntries = new ArrayList<>();

        for (int i = 0; i < diseases.size(); i++) {
            List<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(i, i+1));



            int[] colors = ColorTemplate.MATERIAL_COLORS;
            int color = colors[i % colors.length];

            String label = diseases.get(i);
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.label = label;
            legendEntry.formColor = color;
            legendEntries.add(legendEntry);

            BarDataSet dataSet = new BarDataSet(entries, label);
            dataSet.setColor(color);

            dataSets.add(dataSet);
        }

        BarData barData = new BarData(dataSets);
        barData.getGroupWidth(0f, 0f);


        // 設定動畫
        horizontalBarChart.animateY(1000);

        // 設定其他屬性
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getAxisLeft().setEnabled(false);    // Hide the left Y-axis
        horizontalBarChart.getLegend().setEnabled(false);
//        horizontalBarChart.getLegend().setWordWrapEnabled(true);


        // 設定橫條形圖數據
        horizontalBarChart.setData(barData);

        LegendAdapter legendAdapter = new LegendAdapter(legendEntries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rv_legend = findViewById(R.id.rv_legend);
        rv_legend.setLayoutManager(linearLayoutManager);
        rv_legend.setAdapter(legendAdapter);
    }

}