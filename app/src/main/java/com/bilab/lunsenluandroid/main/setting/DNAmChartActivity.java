package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.LinearGauge;
import com.anychart.core.cartesian.series.Box;
import com.anychart.core.cartesian.series.Marker;
import com.anychart.enums.Anchor;
import com.anychart.enums.Layout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Orientation;
import com.anychart.enums.Position;
import com.anychart.scales.OrdinalColor;
import com.bilab.lunsenluandroid.R;
import com.bilab.lunsenluandroid.conf.Constant;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNAmChartActivity extends AppCompatActivity {
    private final String _TAG = this.getClass().getSimpleName();

    // ============== boxDataEntry ===============
    private ArrayList<Double> _lows, _highs;
    private ArrayList<Double> _q1, _q2, _q3;
    private ArrayList<ArrayList<Double>> _outliers;

    // ============== box properties ================
    private ArrayList<String> _names, _colors;
    private ArrayList<Double> _xPos;

    // =============== personal Gene CT values ================
    private Boolean _hasGeneValue = true; // true if gets intent values
    private Double _dOTX1, _dZNF154, _dZIC4;
    private ArrayList<Double> _markers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnam_chart);
        Objects.requireNonNull(getSupportActionBar()).hide();

        loadConfig();   // load config for data members
        loadIntent();   // load personal gene value if exists

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian boxChart = AnyChart.box();
        boxChart.xAxis(0).staggerMode(true);

        draw(boxChart);

        if(_hasGeneValue)
            markGene(boxChart);
        anyChartView.setChart(boxChart);

        // remove watermark "AnyChart Trial Version"
        boxChart.credits().enabled(false);
        boxChart.legend().enabled(true);

        AnyChartView linearColorScale = findViewById(R.id.any_chart_liner_color_scale);
        APIlib.getInstance().setActiveAnyChartView(linearColorScale);
        LinearGauge linearGauge = AnyChart.linear();


        linearGauge.layout(Layout.HORIZONTAL);

        linearGauge.label(0)
                .position(Position.LEFT_CENTER)
                .anchor(Anchor.LEFT_CENTER)
                .offsetY("-50px")
                .offsetX("50px")
                .fontColor("#777777")
                .fontSize(17);
        linearGauge.label(0).text("罹癌風險");

//        linearGauge.label(1)
//                .position(Position.LEFT_CENTER)
//                .anchor(Anchor.LEFT_CENTER)
//                .offsetY("40px")
//                .offsetX("50px")
//                .fontColor("#777777")
//                .fontSize(17);
//        linearGauge.label(1).text("Drought Hazard");
//
//        linearGauge.label(2)
//                .position(Position.RIGHT_CENTER)
//                .anchor(Anchor.RIGHT_CENTER)
//                .offsetY("40px")
//                .offsetX("50px")
//                .fontColor("#777777")
//                .fontSize(17);
//        linearGauge.label(2).text("Flood Hazard");

        OrdinalColor scaleBarColorScale = OrdinalColor.instantiate();
        scaleBarColorScale.ranges(new String[]{
                "{ from: 0, to: 25, color: [ '#2AD62A', '#CAD70b'] }",
                "{ from: 25, to: 50, color: ['#CAD70b', '#FFD700'] }",
                "{ from: 50, to: 75, color: ['#FFD700', '#EB7A02'] }",
                "{ from: 75, to: 100, color: ['#EB7A02', '#D81E05'] }"
//                "{ from: 0, to: 10, color: ['red 0.5'] }"
        });

        linearGauge.scaleBar(0)
                .width("5%")
                .colorScale(scaleBarColorScale);

        linearGauge.data(new SingleValueDataSet(new Double[] { 25.7D }));
        linearGauge.marker(0)
                .type(MarkerType.TRIANGLE_DOWN)
                .color("red")
                .offset("-3.5%")
                .zIndex(10);

        linearGauge.scale()
                .minimum(0)
                .maximum(100);
//        linearGauge.scale().ticks

        linearGauge.axis(0)
                .minorTicks(false)
                .width("1%")
                .labels().format("{%value}%");
        linearGauge.axis(0)
                .offset("-1.5%")
                .orientation(Orientation.TOP)
                .labels("top");

        linearGauge.padding(0, 30, 0, 30);

        linearColorScale.setChart(linearGauge);

        // remove watermark "AnyChart Trial Version"
        linearGauge.credits().enabled(false);

    }


    private void draw(Cartesian boxChart) {
        String xName = "正常";

        int size = _q1.size();
        for(int i = 0; i < size; i++) {
            if(i < size/2)
                xName = "正常";
            else
                xName = "膀胱癌";

            // OTX1_N
            List<DataEntry> data = new ArrayList<>();
            data.add(new CustomBoxDataEntry(xName, _lows.get(i), _q1.get(i), _q2.get(i), _q3.get(i), _highs.get(i), _outliers.get(i).toArray(new Double[0])));

            Box box = boxChart.box(data);
//            box.fill(_colors[i]);
//            box.stroke(_colors[i]);
//            box.stemStroke();
//            box.medianStroke("#000000");
            box.color(_colors.get(i));
            box.whiskerWidth("20%");
            box.name(_names.get(i));
            box.xPointPosition(_xPos.get(i));

            if(i >= size/2)
                box.legendItem().enabled(false);

        }
    }

    private void markGene(Cartesian boxChart) {
        String xName;

        int size = _q1.size();
        for(int i = 0; i < size; i++) {
            if(i < size/2)
                xName = "正常";
            else
                xName = "膀胱癌";

            List<DataEntry> data = new ArrayList<>();
            data.add(new ValueDataEntry(xName, _markers.get(i)));

            Marker marker = boxChart.marker(data);
            marker.color("#E80003");
            marker.type(MarkerType.STAR4);
            marker.xPointPosition(_xPos.get(i));
            marker.legendItem().enabled(false);
        }
    }


    private class CustomBoxDataEntry extends DataEntry {
        CustomBoxDataEntry(String x, Double low, Double q1, Double median, Double q3, Double high, Double[] outliers) {
            setValue("x", x);
            setValue("low", low);
            setValue("q1", q1);
            setValue("median", median);
            setValue("q3", q3);
            setValue("high", high);
            setValue("outliers", outliers);
        }
    }

    private void loadIntent() {
        Intent receiverIntent = getIntent();
        if(receiverIntent.getExtras() == null) {
            _hasGeneValue = false;
            return;
        }

        _dOTX1 = receiverIntent.getDoubleExtra(Constant.OTX1, 1E9);
        _dZNF154 = receiverIntent.getDoubleExtra(Constant.ZNF154, 1E9);
        _dZIC4 = receiverIntent.getDoubleExtra(Constant.ZIC4, 1E9);

        _markers = new ArrayList<>(List.of(_dOTX1, _dZNF154, _dZIC4));
        _markers.addAll(_markers);
    }

    private void loadConfig() {
        Log.d("7788", "sdfasdfasdfasdf");
        // Load properties from assets
        Properties properties = new Properties();
        AssetManager assetManager = getAssets();

        try {
            InputStream inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ================ Reads Values ==================
        try {
            // ==================== _lows ======================
            String[] lowValues = properties.getProperty("lows", "").split(", ");

            // Convert to ArrayList<Double>
            _lows = new ArrayList<>();
            for (String value : lowValues) {
                if (value.isEmpty())
                    throw new IllegalArgumentException("Empty value found in lows.");
                _lows.add(Double.parseDouble(value));
            }
            Log.d("5566", "lows: " + _lows.toString());

            // ==================== _highs ========================
            String[] highValues = properties.getProperty("highs", "").split(", ");

            // convert to ArrayList<Double>
            _highs = new ArrayList<>();
            for(String value : highValues) {
                if (value.isEmpty())
                    throw new IllegalArgumentException("Empty value found in highs.");
                _highs.add(Double.parseDouble(value));
            }
            Log.d("5566", "highs: " + _highs.toString());


            // ===================== _q1 =======================
            String[] q1s = properties.getProperty("q1", "").split(", ");

            // convert to ArrayList<Double>
            _q1 = new ArrayList<>();
            for(String q1 : q1s) {
                if (q1.isEmpty())
                    throw new IllegalArgumentException("Empty value found in q1.");
                _q1.add(Double.parseDouble(q1));
            }
            Log.d("5566", "q1: " + _q1.toString());


            // ===================== _q2 =======================
            String[] q2s = properties.getProperty("q2", "").split(", ");

            // convert to ArrayList<Double>
            _q2 = new ArrayList<>();
            for(String q2 : q2s) {
                if (q2.isEmpty())
                    throw new IllegalArgumentException("Empty value found in q2.");
                _q2.add(Double.parseDouble(q2));
            }
            Log.d("5566", "q2: " + _q2.toString());

            // ===================== _q3 =======================
            String[] q3s = properties.getProperty("q3", "").split(", ");

            // convert to ArrayList<Double>
            _q3 = new ArrayList<>();
            for(String q3 : q3s) {
                if (q3.isEmpty())
                    throw new IllegalArgumentException("Empty value found in q3.");
                _q3.add(Double.parseDouble(q3));
            }
            Log.d("5566", "q3: " + _q3.toString());

            // ================= _outliers ================
            String outliers = properties.getProperty("outliers", "");
            // add value check
            Log.d("5566", "outlier: " + outliers);
            Pattern pattern = Pattern.compile("\\{(.*?)\\}");
            Matcher matcher = pattern.matcher(outliers);

            // convert to ArrayList<ArrayList<Double>>
            _outliers = new ArrayList<>();
            while(matcher.find()) {
                ArrayList<Double> group = new ArrayList<>();
                Log.d("5566", "matcher.group(1): " + matcher.group(1));
                String[] values = matcher.group(1).split(", ");
                Log.d("5566", "values: " + Arrays.toString(values));
                for (String value : values) {
                    Log.d("5566", "value: " + value);
                    if(!value.isEmpty())
                        group.add(Double.parseDouble(value));
                }
                _outliers.add(group);
            }
            Log.d("5566", "outliers: " + _outliers.toString());

            // ==================== _names ======================
            String[] names = properties.getProperty("names", "").split(", ");

            // Convert to ArrayList<String>
            _names = new ArrayList<>(List.of(names));
            _names.addAll(_names);
            Log.d("5566", "_names: " + _names);

            // ==================== _colors ======================
            String[] colors = properties.getProperty("colors", "").split(", ");

            // Convert to ArrayList<String>
            _colors = new ArrayList<>(List.of(colors));
            _colors.addAll(_colors);
            Log.d("5566", "_colors: " + _colors);

            // ==================== _lows ======================
            String[] xPos = properties.getProperty("xPos", "").split(", ");

            // Convert to ArrayList<Double>
            _xPos = new ArrayList<>();
            for (String value : xPos) {
                if (value.isEmpty())
                    throw new IllegalArgumentException("Empty value found in lows.");
                _xPos.add(Double.parseDouble(value));
            }
            _xPos.addAll(_xPos);
            Log.d("5566", "_xPos: " + _xPos);
        } catch (Exception e) {
            Log.d(_TAG, e.toString());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); // Show Toast message
        }
    }
}