package com.bilab.lunsenluandroid.main.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.BoxDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Box;
import com.bilab.lunsenluandroid.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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

        boxChart.title("Top 10 Jobs Salaries Grades Per Year Calisota, USA");

        boxChart.xAxis(0).staggerMode(true);

        List<DataEntry> data = new ArrayList<>();
        data.add(new CustomBoxDataEntry("正常", 20000, 26000, 27000, 32000, 38000, new Integer[]{50000, 52000}));
//        data.add(new CustomBoxDataEntry("Dental Hygienist", 24000, 28000, 32000, 38000, 42000, new Integer[]{48000}));
//        data.add(new CustomBoxDataEntry("Computer Systems Analyst", 40000, 49000, 62000, 73000, 88000, new Integer[]{32000, 29000, 106000}));
//        data.add(new CustomBoxDataEntry("Physical Therapist", 52000, 59000, 65000, 74000, 83000, new Integer[]{91000}));
//        data.add(new CustomBoxDataEntry("Software Developer", 45000, 54000, 66000, 81000, 97000, new Integer[]{120000}));
//        data.add(new CustomBoxDataEntry("Information Security Analyst", 47000, 56000, 69000, 85000, 100000, new Integer[]{110000, 115000, 32000}));
//        data.add(new CustomBoxDataEntry("Physician Assistant", 67000, 72000, 84000, 95000, 110000, new Integer[]{57000, 54000}));
//        data.add(new CustomBoxDataEntry("Dentist", 75000, 99000, 123000, 160000, 210000, new Integer[]{220000, 70000}));
//        data.add(new CustomBoxDataEntry("Physician", 58000, 96000, 130000, 170000, 200000, new Integer[]{42000, 210000, 215000}));

        Box box = boxChart.box(data);
        box.whiskerWidth("20%");

        List<DataEntry> data2 = new ArrayList<>();
        data2.add(new CustomBoxDataEntry("正常", 24000, 28000, 32000, 38000, 42000, new Integer[]{48000}));

        Box box2 = boxChart.box(data2);
        box2.fill("#00FF00");
        box2.stroke("#00FF00");
        box2.whiskerWidth("20%");

        List<DataEntry> data3 = new ArrayList<>();
        data3.add(new CustomBoxDataEntry("正常", 40000, 49000, 62000, 73000, 88000, new Integer[]{32000, 29000, 106000}));

        Box box3 = boxChart.box(data3);
        box3.fill("#FF5733");
        box3.stroke("#FF5733");
        box3.whiskerWidth("20%");

        List<DataEntry> data4 = new ArrayList<>();
        data4.add(new CustomBoxDataEntry("確診", 24000, 28000, 32000, 38000, 42000, new Integer[]{48000}));

        Box box4 = boxChart.box(data4);
        box4.fill("#0000FF");
        box4.stroke("#0000FF");
        box4.whiskerWidth("20%");

        List<DataEntry> data5 = new ArrayList<>();
        data5.add(new CustomBoxDataEntry("確診", 45000, 54000, 66000, 81000, 97000, new Integer[]{120000}));

        Box box5 = boxChart.box(data5);
        box5.fill("#00FF00");
        box5.stroke("#00FF00");
        box5.whiskerWidth("20%");

        List<DataEntry> data6 = new ArrayList<>();
        data6.add(new CustomBoxDataEntry("確診", 75000, 99000, 123000, 160000, 210000, new Integer[]{220000, 70000}));

        Box box6 = boxChart.box(data6);
        box6.fill("#FF5733");
        box6.stroke("#FF5733");
        box6.whiskerWidth("20%");

        anyChartView.setChart(boxChart);

        // remove watermark "AnyChart Trial Version"
        boxChart.credits().enabled(false);
    }

    private class CustomBoxDataEntry extends BoxDataEntry {
        CustomBoxDataEntry(String x, Integer low, Integer q1, Integer median, Integer q3, Integer high, Integer[] outliers) {
            super(x, low, q1, median, q3, high);
            setValue("outliers", outliers);
        }
    }
}