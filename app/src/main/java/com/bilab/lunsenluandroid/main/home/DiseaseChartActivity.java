package com.bilab.lunsenluandroid.main.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;

import android.util.Log;
import android.widget.TextView;

import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.main.DiseaseData;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DiseaseChartActivity extends AppCompatActivity {
    private ArrayList<Integer> _colors;
    private ArrayList<String> _cancer_diseases, _person_diseases;
    private String _cancer;
    private Intent _receiverIntent;
    private RecyclerView rv_legend; // for BarChart
    private TextView tv_cancer, tv_odds_ratio, tv_risk;
    private HorizontalBarChart horizontalBarChart;
    private PieChart _peronalPieChart, _averagePieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_chart);
        Objects.requireNonNull(getSupportActionBar()).hide();

        _receiverIntent = getIntent();
        if (_receiverIntent == null || _receiverIntent.getComponent() == null) {
            Log.d("DiseaseChartActivity", "Do not receive any Intent.");
            throw new NullPointerException();
        }

        registerUI();
        setValue();
        setUI();
        setHorizontalBarChart();
        setPieChart();
    }


    private void registerUI() {
        tv_cancer = findViewById(R.id.tv_cancer_diseases);
        tv_odds_ratio = findViewById(R.id.tv_odds_ratio);

        // charts
        horizontalBarChart = findViewById(R.id.horizontalBarChart);
        _peronalPieChart = findViewById(R.id.piechart_personal);
        _averagePieChart = findViewById(R.id.piechart_avg);

        rv_legend = findViewById(R.id.rv_legend);
    }

    private void setValue() {
        _cancer = _receiverIntent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY);
        _cancer_diseases = DiseaseData.getInstance().getCancerDiseaseList(_cancer);
        _person_diseases = Person.getInstance().getDiseases(_cancer);
        _colors = generateColors();
    }

    private ArrayList<Integer> generateColors() {
        ArrayList<Integer> colors = new ArrayList<>();

        // Get the hue value of the target color
        float[] targetHsv = new float[3];
        Color.colorToHSV(Color.parseColor("#6A9F9B"), targetHsv);
        float targetHue = targetHsv[0];
        float targetSaturation = targetHsv[1];
        float targetValue = targetHsv[2];

        // Range for hue and saturation values
        float hueGap = 10.0f;


        Random random = new Random();
        for(int i = 0; i < _cancer_diseases.size(); i++) {
            // Generate random hue and saturation values within the specified ranges
            float hue = targetHue  + i * hueGap;

            float[] hsv = new float[]{hue, targetSaturation, targetValue}; // Random hue in the warm color range
            int color = Color.HSVToColor(hsv);

            colors.add(color);
        }
        return colors;
    }

    private void setUI() {
        tv_cancer.setText(_cancer);
    }

    // include customized legends by RecyclerView
    private void setHorizontalBarChart() {
        // Set Left-Y Axis (圖形上方)
        YAxis leftYAxis = horizontalBarChart.getAxisLeft();
        leftYAxis.setDrawGridLines(false);

        // Set Right-Y Axis (圖形下方)
        YAxis rightYAxis = horizontalBarChart.getAxisRight();
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setAxisMaxValue(10);
        rightYAxis.setAxisMinValue(10);

        // Ensure Y-axis starts from 0
        leftYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMinimum(0f);

        // Set X Axis (圖形左方)
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        // Set bar values
        List<IBarDataSet> dataSets = new ArrayList<>();         // one bar in one data-set

        for (int i = 0; i < _cancer_diseases.size(); i++) {
            List<BarEntry> entries = new ArrayList<>();     // the size is always one(bar)
            entries.add(new BarEntry(i, i+1));

            BarDataSet barDataSet = new BarDataSet(entries, _cancer_diseases.get(i));   // (BarEntry, String)
            barDataSet.setColor(_colors.get(i));

            dataSets.add(barDataSet);       // add a dataset in which only one bar exists
        }

        // set bar UI
        BarData barData = new BarData(dataSets);
        barData.getGroupWidth(0f, 0f);
        barData.setBarWidth(0.5f);

        // 設定動畫
        horizontalBarChart.animateY(1000);

        // 設定其他屬性
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getAxisLeft().setEnabled(false);    // Hide the left Y-axis
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setClickable(false);

        // 設定橫條形圖數據
        horizontalBarChart.setData(barData);

        // set customized legends with RecyclerView
        List<LegendEntry> legendEntries = new ArrayList<>();

        for (int i = 0; i < _cancer_diseases.size(); i++) {
            List<BarEntry> entries = new ArrayList<>();
            entries.add(new BarEntry(i, i+1));

            LegendEntry legendEntry = new LegendEntry();
            legendEntry.label = _cancer_diseases.get(i);    // return String
            legendEntry.formColor = _colors.get(i);         // return int

            legendEntries.add(legendEntry);
        }

        LegendAdapter legendAdapter = new LegendAdapter(legendEntries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_legend.setLayoutManager(linearLayoutManager);
        rv_legend.setAdapter(legendAdapter);

    }

    private void setPieChart() {
        // Create data entries for the chart
        ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        float percent = 100f / _cancer_diseases.size();

        for(int i = 0; i < _cancer_diseases.size(); i++) {
//            if(i < _person_diseases.size())
//                colors.add(Color.RED);
//            else
//                colors.add(Color.WHITE);
            entries.add(new PieEntry(percent, ""));
        }


        // Create a dataset with the data entries
        PieDataSet dataSet = new PieDataSet(entries, "");
//        dataSet.setColors(_colors);



        // Create PieData object
        PieData pieData = new PieData(dataSet);
//        pieData.setValueFormatter(new PercentFormatter(_peronalPieChart)); // Format values as percentages
//        pieData.setDrawValues(false);
//
//        // Customize the chart
//        _peronalPieChart.setData(pieData);
////        pieChart.setUsePercentValues(true);
//        _peronalPieChart.getDescription().setEnabled(false);
//        _peronalPieChart.setHoleRadius(90f);
//        _peronalPieChart.setTransparentCircleRadius(45f);
//        _peronalPieChart.getLegend().setEnabled(false);
//
//        // Set center text with percentage
//        _peronalPieChart.setCenterText(String.format("%.1f%%", 20f));   // printing percent(%) by escaped sign %
//        _peronalPieChart.setCenterTextSize(16f);
//
//
//        // Set the rotation angle to 0 degrees (clockwise)
//        _peronalPieChart.setRotationAngle(0f);
//
//        // Add animation
//        _peronalPieChart.animateY(1000, Easing.EaseInOutQuad); // Animate the Y-axis with ease in-out interpolation
//
//        // Refresh the chart
//        _peronalPieChart.invalidate();






        // Create PieData object
        PieData averagePieData = new PieData(dataSet);
//        averagePieData.setValueFormatter(new PercentFormatter(_averagePieChart)); // Format values as percentages
//        averagePieData.setDrawValues(false);
//
//        // Customize the chart
//        _averagePieChart.setData(averagePieData);
////        pieChart.setUsePercentValues(true);
//        _averagePieChart.getDescription().setEnabled(false);
//        _averagePieChart.setHoleRadius(90f);
//        _averagePieChart.setTransparentCircleRadius(45f);
//        _averagePieChart.getLegend().setEnabled(false);
//
//        // Set center text with percentage
//        _averagePieChart.setCenterText(String.format("%.1f%%", 20f));   // printing percent(%) by escaped sign %
//        _averagePieChart.setCenterTextSize(16f);
//
//        // Set the rotation angle to 0 degrees (clockwise)
//        _averagePieChart.setRotationAngle(0f);
//
//        // Add animation
//        _averagePieChart.animateY(1000, Easing.EaseInOutQuad); // Animate the Y-axis with ease in-out interpolation
//
//        // Refresh the chart
//        _averagePieChart.invalidate();

        initPieChart(_averagePieChart, averagePieData, Color.RED);
        initPieChart(_peronalPieChart, pieData, Color.YELLOW);

        _averagePieChart.setData(averagePieData);
        _peronalPieChart.setData(pieData);
    }

    private void initPieChart(PieChart pieChart, PieData pieData,  int color) {
        // Create data entries for the chart
        ArrayList<Integer> colors = new ArrayList<>();

        float percent = 100f / _cancer_diseases.size();

        for(int i = 0; i < _cancer_diseases.size(); i++) {
            if(i < _person_diseases.size())
                colors.add(color);
            else
                colors.add(Color.LTGRAY);
//            entries.add(new PieEntry(percent, ""));
        }


        // Create a dataset with the data entries
        PieDataSet dataSet = (PieDataSet) pieData.getDataSet();
        dataSet.setColors(colors);



        // Create PieData object
        pieData.setValueFormatter(new PercentFormatter(pieChart)); // Format values as percentages
        pieData.setDrawValues(false);


        // Customize the chart
//        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(90f);
        pieChart.setTransparentCircleRadius(45f);
        pieChart.getLegend().setEnabled(false);

        // Set center text with percentage
        pieChart.setCenterText(String.format("%.1f%%", 20f));   // printing percent(%) by escaped sign %
        pieChart.setCenterTextSize(16f);

        // Set the rotation angle to 0 degrees (clockwise)
        pieChart.setRotationAngle(0f);

        // Add animation
        pieChart.animateY(1000, Easing.EaseInOutQuad); // Animate the Y-axis with ease in-out interpolation

        // Refresh the chart
        pieChart.invalidate();
    }

}