package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.BoxDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Box;
import com.anychart.core.cartesian.series.Marker;
import com.anychart.core.ui.LegendItem;
import com.anychart.core.ui.legend.LegendItemProvider;
import com.anychart.enums.LegendItemIconType;
import com.anychart.enums.MarkerType;
import com.anychart.graphics.vector.Fill;
import com.anychart.graphics.vector.Stroke;
import com.anychart.graphics.vector.hatchfill.HatchFillType;
import com.bilab.lunsenluandroid.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DNAmChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnam_chart);
        Objects.requireNonNull(getSupportActionBar()).hide();


        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        Cartesian boxChart = AnyChart.box();

//        boxChart.title("Top 10 Jobs Salaries Grades Per Year Calisota, USA");

        boxChart.xAxis(0).staggerMode(true);

        // OTX1_N
        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomBoxDataEntry("正常", 0.0682, 0.0773, 0.1076, 0.4034, 0.5329, new Double[]{}));

        Box box = boxChart.box(data);
        box.whiskerWidth("20%");
        box.name("OTX1");
        box.xPointPosition(0.35);

        // GALR1_N
        List<DataEntry> data2 = new ArrayList<>();
        data2.add(new CustomBoxDataEntry("正常", 0.0720, 0.1117, 0.2152, 0.5517, 0.6560, new Double[]{}));

        Box box2 = boxChart.box(data2);
        box2.fill("#00FF00");
        box2.stroke("#00FF00");
        box2.whiskerWidth("20%");
        box2.name("GLAR1");
        box2.xPointPosition(0.5);

        // ZIC4_N
        List<DataEntry> data3 = new ArrayList<>();
        data3.add(new CustomBoxDataEntry("正常", 0.1020, 0.1253, 0.1870, 0.5095, 0.5918, new Double[]{}));

        Box box3 = boxChart.box(data3);
        box3.fill("#FFE200");
        box3.stroke("#FFE200");
        box3.whiskerWidth("20%");
        box3.name("ZIC4");
        box3.xPointPosition(0.65);


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
}