package com.bilab.lunsenluandroid.main.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;

import com.bilab.lunsenluandroid.conf.Person;
import com.bilab.lunsenluandroid.R;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bilab.lunsenluandroid.conf.Constant;
import com.bilab.lunsenluandroid.main.DiseaseData;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarLineChartBase;
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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.android.material.slider.LabelFormatter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.Properties;

public class DiseaseChartActivity extends AppCompatActivity {
    private ArrayList<Integer> _colors;
    private ArrayList<String> _cancer_diseases, _person_diseases;
    private String [] _cancerICD9s;
    private String _cancer;
    private Intent _receiverIntent;
    private TextView tv_cancer, tv_odds_ratio, tv_risk;
    private HorizontalBarChart horizontalBarChart;
    private PieChart _peronalPieChart;

    // =============== model attributes =================
    private Map<String, Double> _wDiseases;
    private Double _bias;
    private Double _pRisk;




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

        setValue();
        loadConfig();

        registerUI();

        setUI();
        setHorizontalBarChart();
        setPieChart();

        computeRise();
    }


    private void registerUI() {
        tv_cancer = findViewById(R.id.tv_cancer_diseases);
        tv_odds_ratio = findViewById(R.id.tv_odds_ratio);

        // charts
        horizontalBarChart = findViewById(R.id.horizontalBarChart);
        _peronalPieChart = findViewById(R.id.piechart_personal);

    }

    private void setValue() {
        _cancer = _receiverIntent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY);
        _cancer_diseases = DiseaseData.getInstance().getCancerDiseaseList(_cancer);
        _person_diseases = Person.getInstance().getDiseaseNames(_cancer);
        _cancerICD9s = DiseaseData.getInstance().getCancerICD9(_cancer);
        _colors = generateColors();

        _pRisk = Person.getInstance().getRisk(_cancer);

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
        float hueGap = 30.0f;

        String[] colorss = {"#FF5733", "#FFBD33", "#FFF033", "#DBFF33", "#75FF33", "#33FF57", "#33FFBD"};

//        for(int i = 0; i < _cancer_diseases.size(); i++) {
//            // Generate hue and saturation values within the specified ranges
//            float hue = (targetHue  + i * hueGap ) % 360f;
//
//            float[] hsv = new float[]{hue, targetSaturation, targetValue}; // Random hue in the warm color range
//            int color = Color.HSVToColor(hsv);
//
//            colors.add(color);
//        }

        for(int i = 0; i < _cancer_diseases.size(); i++) {
            // Generate hue and saturation values within the specified ranges
            colors.add(Color.parseColor(colorss[i % colorss.length]));
        }
        return colors;
    }

    private void setUI() {
        if(_cancer.equals(Constant.UTERUS))
            tv_cancer.setText(String.format("%s - 共病風險分析", "子宮內膜癌"));
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
        rightYAxis.setDrawLabels(false);        // hide y-axis scales
        rightYAxis.setAxisMaxValue(16);
        rightYAxis.setAxisMinValue(0);




        // Ensure Y-axis starts from 0
        leftYAxis.setAxisMinimum(0f);
        rightYAxis.setAxisMinimum(0f);

        // Set X Axis (圖形左方)
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);     // set x-axis to the right
        xAxis.setXOffset(-353);         // align the labels to the left
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);   // hide x-axis line
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.GRAY);


        // Set bar values
        List<IBarDataSet> dataSets = new ArrayList<>();         // one bar in one data-set

        DiseaseData diseaseData = DiseaseData.getInstance();
        int barCount = (_cancer_diseases.size() - 1) * 2;

        for (int i = 0; i < barCount; i++) {     // minus the "無上述症狀"
            List<BarEntry> entries = new ArrayList<>();     // the size is always one(bar)
            int pos = barCount - i;
            int color;
            // the title bar config
            if (i % 2 == 0) {
                entries.add(new BarEntry(pos, 0.0f));
                color = Color.LTGRAY;
            }
            // the data bar config
            else {
                if(_person_diseases.contains(_cancer_diseases.get(i / 2))) {
                    entries.add(new BarEntry(pos, diseaseData.getICD9OR(_cancer, _cancerICD9s[i / 2]).floatValue()));
                    color = Color.parseColor("#FF3333");
                }
                else {
                    entries.add(new BarEntry(pos, 0.15f));
                    color = Color.GRAY;
                }
            }

            BarDataSet barDataSet = new BarDataSet(entries, _cancer_diseases.get(i / 2));   // (BarEntry, String)
            barDataSet.setColor(color);
            barDataSet.setDrawValues(false);    // disable showing value at the end of the bar
            barDataSet.setAxisDependency(rightYAxis.getAxisDependency());

            dataSets.add(barDataSet);       // add a dataset in which only one bar exists
        }


        // set bar UI
        BarData barData = new BarData(dataSets);
        barData.getGroupWidth(0f, 0f);
        barData.setBarWidth(1.0f);

        xAxis.setValueFormatter(new ValueFormatter() {
            int size = _cancer_diseases.size() - 1;
            @Override
            public String getFormattedValue(float value) {
                if((int)(value) % 2 == 0)
                    return _cancer_diseases.get(size - ((int)value / 2));
                else
                    return "";
            }
        });



        // 設定動畫
//        horizontalBarChart.animateY(1000);

        // 設定其他屬性
        horizontalBarChart.setDrawValueAboveBar(true);
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getAxisLeft().setEnabled(false);    // Hide the left Y-axis
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.setClickable(false);
        horizontalBarChart.setDrawBorders(true);
        horizontalBarChart.setBorderColor(Color.GRAY);
        horizontalBarChart.setBorderWidth(0.2f);

        // 設定橫條形圖數據
        horizontalBarChart.setData(barData);
    }


    private void setPieChart() {
        // Create data entries for the chart
        ArrayList<PieEntry> entries = new ArrayList<>();

        float percent = 100f / _cancer_diseases.size();

        for(int i = 0; i < _cancer_diseases.size(); i++)
            entries.add(new PieEntry(percent, ""));

        PieDataSet personalPieDataSet = new PieDataSet(entries, "");
        PieData personalPieData = new PieData(personalPieDataSet);
        
        // set common attribute
        String text;
        if(_pRisk <= 50.0D)
            text = "低風險";
        else if(_pRisk <= 75.0D)
            text = "中風險";
        else
            text = "高風險";
        initPieChart(_peronalPieChart, personalPieData, text);

        // set pie colors
        int chartRatio = (int) Math.ceil((_cancer_diseases.size() - 1) * _pRisk / 100);
        int color;
        if(_pRisk <= 50.0D)
            color = Color.GREEN;
        else if(_pRisk <= 75.0D)
            color = Color.YELLOW;
        else
            color = Color.RED;
        setPieColor(personalPieData, chartRatio, color);

        // draw charts
        _peronalPieChart.setData(personalPieData);
    }

    private void initPieChart(PieChart pieChart, PieData pieData, String text) {

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
        pieChart.setCenterText(text);   // printing percent(%) by escaped sign %
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

    private Double computeRise() {
        Person person = Person.getInstance();
        ArrayList<String> icd9s = person.getDiseaseICD9(_cancer);

        Double fw = 0.0D;
        for(var entry : _wDiseases.entrySet()) {
            if(icd9s.contains(entry.getKey()))
                fw += entry.getValue() * 1.0D;
        }
        fw += _bias;

        Double pRisk = sigmoid(fw) * 100;
        Log.d("qwer", "pRisk: " + pRisk);

        return pRisk;
    }

    private Double sigmoid(Double x) {
        return 1 / (1 + Math.exp(-x));
    }


    private void loadConfig() {
        // Load properties from assets
        Properties properties = new Properties();
        AssetManager assetManager = getAssets();

        try {
            InputStream inputStream = assetManager.open("disease_chart_activity.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get lower case cancer name
        String cancer = _cancer.toLowerCase();

        // ================ Reads model weights and bias ==================
        try {
            // ===================== weights ========================
            String weights = cancer + ".model.weights";   // e.g. bladder.model.weights
            String[] wDiseases = properties.getProperty(weights, "").replaceAll("[()]", "").split(",\\s*");;

            // Create a map to store tuples
            _wDiseases = new HashMap<>();

            // Iterate over tuple array and populate the map
            for (int i = 0; i < wDiseases.length; i += 2) {
                String key = wDiseases[i];
                double value = Double.parseDouble(wDiseases[i + 1]);
                _wDiseases.put(key, value);
            }

            // Print the map
            for (Map.Entry<String, Double> entry : _wDiseases.entrySet()) {
                Log.d("qwer", entry.getKey() + " -> " + entry.getValue());
            }

            // ====================== bias ==============================
            String modelBias = cancer + ".model.bias";   // e.g. bladder.model.bias
            String bias = properties.getProperty(modelBias, "");

            // convert to Double
            if (bias.isEmpty())
                throw new IllegalArgumentException("Empty value found in bias.");
            _bias = Double.parseDouble(bias);
            Log.d("qwer", "bias: " + _bias);

        } catch (Exception e) {
//            Log.d(_TAG, e.toString());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); // Show Toast message
        }
    }
}