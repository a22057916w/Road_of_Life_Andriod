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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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

        Log.d("qwer", _cancer_diseases.toString());
        Log.d("qwer", _person_diseases.toString());
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
        if(_cancer.equals(Constant.UTERUS))
            tv_cancer.setText(String.format("%s - 共病風險分析", "子宮癌"));
        else if(_cancer.equals(Constant.OVARY))
            tv_cancer.setText(String.format("%s - 共病風險分析", "卵巢癌"));
        else if(_cancer.equals(Constant.BLADDER))
            tv_cancer.setText(String.format("%s - 共病風險分析", "膀胱癌"));
        else if(_cancer.equals(Constant.RECTUM))
            tv_cancer.setText(String.format("%s - 共病風險分析", "大腸癌"));
        else
            tv_cancer.setText(Constant.NONE);
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
            entries.add(new BarEntry(_cancer_diseases.size() - 1 - i, i+1));

            int textColor;
            if(_person_diseases.contains(_cancer_diseases.get(i)))
                textColor = Color.RED;
            else
                textColor = Color.BLACK;

            BarDataSet barDataSet = new BarDataSet(entries, _cancer_diseases.get(i));   // (BarEntry, String)
            barDataSet.setColor(_colors.get(i));
            barDataSet.setValueTextColor(textColor);

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

        float percent = 100f / _cancer_diseases.size();

        for(int i = 0; i < _cancer_diseases.size(); i++)
            entries.add(new PieEntry(percent, ""));

        PieDataSet personalPieDataSet = new PieDataSet(entries, "");
        PieData personalPieData = new PieData(personalPieDataSet);



        ArrayList<PieEntry> average_entries = new ArrayList<>();
        for(int i = 0; i < _cancer_diseases.size(); i++)
            average_entries.add(new PieEntry(percent, ""));

        PieDataSet averagePieDataSet = new PieDataSet(average_entries, "");
        PieData averagePieData = new PieData(averagePieDataSet);

        // set common attribute
        initPieChart(_averagePieChart, averagePieData);
        initPieChart(_peronalPieChart, personalPieData);

        // set pie colors
        setPieColor(averagePieData, 2, Color.YELLOW);
        setPieColor(personalPieData, _person_diseases.size(), Color.RED);

        // draw charts
        _averagePieChart.setData(averagePieData);
        _peronalPieChart.setData(personalPieData);
    }

    private void initPieChart(PieChart pieChart, PieData pieData) {

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

    private void setPieColor(PieData pieData, int pie_count, int color) {
        ArrayList<Integer> colors = new ArrayList<>();

        for(int i = 0; i < _cancer_diseases.size(); i++) {
            if(i < pie_count)
                colors.add(color);
            else
                colors.add(Color.LTGRAY);
        }

        PieDataSet dataSet = (PieDataSet) pieData.getDataSet();
        dataSet.setColors(colors);
    }
}