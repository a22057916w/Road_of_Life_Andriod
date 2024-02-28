package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Box;
import com.bilab.lunsenluandroid.R;


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
    private String[] _name = {"OTX1", "GLAR1", "ZIC4"};
    private String[] _colors = {"#70CCE1", "#00FF00", "#FFE200"};  // blue, green, yellow
    private double[] _xPos = {0.35, 0.5, 0.65};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnam_chart);
        Objects.requireNonNull(getSupportActionBar()).hide();

        loadConfig();   // load config or default values

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian boxChart = AnyChart.box();

        draw(boxChart);

        boxChart.xAxis(0).staggerMode(true);

        // OTX1_N
//        List<DataEntry> data = new ArrayList<>();
//        data.add(new CustomBoxDataEntry("正常", _lows.get(0), _q1.get(0), _q2.get(0), _q3.get(0), _highs.get(0), _outliers.get(0).toArray(new Double[0])));
//
//        Box box = boxChart.box(data);
//        box.whiskerWidth("20%");
//        box.name("OTX1");
//        box.xPointPosition(0.35);
//
//        // GALR1_N
//        List<DataEntry> data2 = new ArrayList<>();
//        data2.add(new CustomBoxDataEntry("正常", 0.0720, 0.1117, 0.2152, 0.5517, 0.6560, new Double[]{}));
//
//        Box box2 = boxChart.box(data2);
//        box2.fill("#00FF00");
//        box2.stroke("#00FF00");
//        box2.whiskerWidth("20%");
//        box2.name("GLAR1");
//        box2.xPointPosition(0.5);
//
//        // ZIC4_N
//        List<DataEntry> data3 = new ArrayList<>();
//        data3.add(new CustomBoxDataEntry("正常", 0.1020, 0.1253, 0.1870, 0.5095, 0.5918, new Double[]{}));
//
//        Box box3 = boxChart.box(data3);
//        box3.fill("#FFE200");
//        box3.stroke("#FFE200");
//        box3.whiskerWidth("20%");
//        box3.name("ZIC4");
//        box3.xPointPosition(0.65);


        // OTX1_T
        List<DataEntry> data4 = new ArrayList<>();
        data4.add(new CustomBoxDataEntry("確診", 0.2955, 0.5970, 0.7005, 0.7980, 0.9514, new Double[]{0.0592, 0.0970, 0.1990, 0.2287}));

        Box box4 = boxChart.box(data4);
        box4.fill("#70CCE1");
        box4.stroke("#70CCE1");
        box4.whiskerWidth("20%");
        box4.medianStroke("#000000");
        box4.name("OTX1");
        box4.legendItem().enabled(false);
        box4.xPointPosition(0.35);

        // GLAR1_T
        List<DataEntry> data5 = new ArrayList<>();
        data5.add(new CustomBoxDataEntry("確診", 0.3349, 0.5964, 0.7161, 0.7322, 0.9463, new Double[]{0.0593, 0.0789, 0.0899, 0.1450, 0.1891, 0.2469}));

        Box box5 = boxChart.box(data5);
        box5.fill("#00FF00");
        box5.stroke("#00FF00");
        box5.whiskerWidth("20%");
        box5.name("GLAR1");
        box5.legendItem().enabled(false);
        box5.xPointPosition(0.5);


        List<DataEntry> data6 = new ArrayList<>();
        data6.add(new CustomBoxDataEntry("確診", 0.1365, 0.4792, 0.6705, 0.7748, 0.9691, new Double[]{}));

        // ZIC4_T
        Box box6 = boxChart.box(data6);
        box6.fill("#FFE200");
        box6.stroke("#FFE200");
        box6.whiskerWidth("20%");
        box6.medianStroke("#000000");
        box6.name("ZIC4");
        box6.legendItem().enabled(false);
        box6.xPointPosition(0.65);

//        List<DataEntry> marker1 = new ArrayList<>();
//        marker1.add(new ValueDataEntry("正常", 0.5));
//
//        Marker marker_n1 = boxChart.marker(marker1);
//        marker_n1.xPointPosition(0.35);
//        marker_n1.legendItem().enabled(false);
//        marker_n1.type(MarkerType.STAR4);
//        marker_n1.color("#E80003");
//
//        List<DataEntry> marker2 = new ArrayList<>();
//        marker2.add(new ValueDataEntry("正常", 0.3));
//
//        Marker marker_n2 = boxChart.marker(marker2);
//        marker_n2.xPointPosition(0.5);
//        marker_n2.legendItem().enabled(false);
//        marker_n2.type(MarkerType.STAR4);
//        marker_n2.color("#E80003");
//
//        List<DataEntry> marker3 = new ArrayList<>();
//        marker3.add(new ValueDataEntry("正常", 0.6));
//
//        Marker marker_n3 = boxChart.marker(marker3);
//        marker_n3.xPointPosition(0.65);
//        marker_n3.legendItem().enabled(false);
//        marker_n3.type(MarkerType.STAR4);
//        marker_n3.color("#E80003");
//
//
//        List<DataEntry> marker4 = new ArrayList<>();
//        marker4.add(new ValueDataEntry("確診", 0.5));
//
//        Marker marker_n4 = boxChart.marker(marker4);
//        marker_n4.xPointPosition(0.35);
//        marker_n4.legendItem().enabled(false);
//        marker_n4.type(MarkerType.STAR4);
//        marker_n4.color("#E80003");
//
//        List<DataEntry> marker5 = new ArrayList<>();
//        marker5.add(new ValueDataEntry("確診", 0.3));
//
//        Marker marker_n5 = boxChart.marker(marker5);
//        marker_n5.xPointPosition(0.5);
//        marker_n5.legendItem().enabled(false);
//        marker_n5.type(MarkerType.STAR4);
//        marker_n5.color("#E80003");
//
//        List<DataEntry> marker6 = new ArrayList<>();
//        marker6.add(new ValueDataEntry("確診", 0.6));
//
//        Marker marker_n6 = boxChart.marker(marker6);
//        marker_n6.xPointPosition(0.65);
//        marker_n6.legendItem().enabled(false);
//        marker_n6.type(MarkerType.STAR4);
//        marker_n6.color("#E80003");


        anyChartView.setChart(boxChart);

        // remove watermark "AnyChart Trial Version"
        boxChart.credits().enabled(false);
        boxChart.legend().enabled(true);


    }

    private void draw(Cartesian boxChart) {
//        List<DataEntry> data = new ArrayList<>();
//        data.add(new CustomBoxDataEntry("正常", _lows.get(1), _q1.get(1), _q2.get(1), _q3.get(1), _highs.get(1), _outliers.get(1).toArray(new Double[0])));

        int size = 2;
        for(int i = 0; i < 3; i++) {
            // OTX1_N
            List<DataEntry> data = new ArrayList<>();
            data.add(new CustomBoxDataEntry("正常", _lows.get(i), _q1.get(i), _q2.get(i), _q3.get(i), _highs.get(i), _outliers.get(i).toArray(new Double[0])));

            Box box = boxChart.box(data);
//            box.fill(_colors[i]);
//            box.stroke(_colors[i]);
            box.stemStroke();
            box.color(_colors[i]);
            box.whiskerWidth("20%");
            box.name(_name[i]);
            box.xPointPosition(_xPos[i]);
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
            Log.d("5566", "dsfsdfsdfds");
            Log.d("5566", "outliers: " + _outliers.toString());
        } catch (Exception e) {
            Log.d(_TAG, e.toString());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); // Show Toast message
        }
    }
}