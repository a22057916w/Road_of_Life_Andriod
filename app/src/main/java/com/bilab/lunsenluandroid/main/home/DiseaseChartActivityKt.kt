package com.bilab.lunsenluandroid.main.home

import android.content.res.AssetManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bilab.lunsenluandroid.R
import com.bilab.lunsenluandroid.conf.Constant
import com.bilab.lunsenluandroid.conf.Person
import com.bilab.lunsenluandroid.main.DiseaseData
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import java.io.IOException
import java.io.InputStream
import java.util.Properties
import kotlin.math.ceil
import kotlin.math.exp

class DiseaseChartActivityKt : AppCompatActivity() {
    private lateinit var cancerDiseases: ArrayList<String>
    private lateinit var personDiseases: ArrayList<String>
    private lateinit var cancerICD9s: Array<String>
    private lateinit var cancer: String
    private lateinit var tvCancer: TextView
    private lateinit var tvOddsRatio: TextView
    private lateinit var horizontalBarChart: HorizontalBarChart
    private lateinit var personalPieChart: PieChart

    // =============== model attributes =================
    private lateinit var wDiseases: Map<String, Double>
    private var bias: Double = 0.0
    private var pRisk: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_chart)
        supportActionBar?.hide()

        if (intent == null) {
            Log.d("DiseaseChartActivity", "Do not receive any Intent.")
            throw NullPointerException()
        }

        setValue()
        loadConfig()

        registerUI()

        setUI()
        setHorizontalBarChart()
        setPieChart()

        computeRisk()
    }

    private fun registerUI() {
        tvCancer = findViewById(R.id.tv_cancer_diseases)
        tvOddsRatio = findViewById(R.id.tv_odds_ratio)

        // charts
        horizontalBarChart = findViewById(R.id.horizontalBarChart)
        personalPieChart = findViewById(R.id.piechart_personal)
    }

    private fun setValue() {
        cancer = intent.getStringExtra(Constant.EXTRA_DISEASE_CATEGORY) ?: ""
        cancerDiseases = DiseaseData.getInstance().getCancerDiseaseList(cancer)
        personDiseases = Person.getInstance().getDiseaseNames(cancer)
        cancerICD9s = DiseaseData.getInstance().getCancerICD9(cancer)

        pRisk = Person.getInstance().getRisk(cancer)

        Log.d("qwer", cancerDiseases.toString())
        Log.d("qwer", personDiseases.toString())
    }

    private fun setUI() {
        val cancerName = when (cancer) {
            Constant.UTERUS -> "子宮內膜癌"
            Constant.OVARY -> "卵巢癌"
            Constant.BLADDER -> "膀胱癌"
            Constant.RECTUM -> "大腸癌"
            Constant.DKD -> "糖尿病腎病變"
            else -> Constant.NONE
        }
        tvCancer.text = if (cancerName != Constant.NONE) "$cancerName - 共病風險分析" else Constant.NONE
    }

    // include customized legends by RecyclerView
    private fun setHorizontalBarChart() {
        horizontalBarChart.apply {
            // Set Left-Y Axis (圖形上方)
            axisLeft.setDrawGridLines(false)

            // Set Right-Y Axis (圖形下方)
            axisRight.setDrawGridLines(false)
            axisRight.setDrawLabels(false)  // hide y-axis scales

            // Ensure Y-axis starts from 0
            axisLeft.axisMinimum = 0f
            axisRight.axisMinimum = 0f

            // Set X Axis
            xAxis.apply {
                position = XAxis.XAxisPosition.TOP
                xOffset = -353f
                setDrawGridLines(false)
                setDrawAxisLine(false)
                textSize = 14f
                textColor = Color.GRAY
            }

            // Set bar values
            val dataSets = mutableListOf<BarDataSet>()      // one bar in one data-set
            val diseaseData = DiseaseData.getInstance()
            val barCount = (cancerDiseases.size - 1) * 2    // 減去 "無上述症狀"

            for (i in 0 until barCount) {
                val entries = mutableListOf<BarEntry>()     // the size is always one(bar)
                val pos = barCount - i
                val color: Int

                // the title bar config
                if (i % 2 == 0) {
                    entries.add(BarEntry(pos.toFloat(), 0.0f))
                    color = Color.LTGRAY
                }
                // the data bar config
                else {
                    if (personDiseases.contains(cancerDiseases[i / 2])) {
                        entries.add(BarEntry(pos.toFloat(), diseaseData.getICD9OR(cancer, cancerICD9s[i / 2]).toFloat()))
                        color = Color.parseColor("#FF3333") // RED
                    }
                    else {
                        entries.add(BarEntry(pos.toFloat(), 0.15f))
                        color = Color.GRAY
                    }
                }

                val barDataSet = BarDataSet(entries, cancerDiseases[i / 2]).apply {
                    this.color = color
                    setDrawValues(false)    // disable showing value at the end of the bar
                    axisDependency = YAxis.AxisDependency.RIGHT
                }
                dataSets.add(barDataSet)    // add a dataset in which only one bar exists
            }

            // Set bar UI
            val barData = BarData(dataSets.toList())
            barData.barWidth = 1.0f

            // 只顯示 title bar 的 value(label)
            xAxis.valueFormatter = object : ValueFormatter() {
                private val size = cancerDiseases.size - 1
                override fun getFormattedValue(value: Float): String {
                    return if (value.toInt() % 2 == 0) cancerDiseases[size - value.toInt() / 2] else ""
                }
            }
            xAxis.labelCount = dataSets.size    // 設定 X 軸標籤的顯示數量

            // 設定其他屬性
            setDrawValueAboveBar(true)
            description.isEnabled = false
            setDrawGridBackground(false)
            axisLeft.isEnabled = false
            legend.isEnabled = false
            isClickable = false
            setDrawBorders(true)
            setBorderColor(Color.GRAY)
//            borderWidth = 0.2f
            setTouchEnabled(false)

            // Set data
            this.data = barData
        }
    }

    private fun setPieChart() {
        val percent = 100f / cancerDiseases.size
        val entries = ArrayList<PieEntry>().apply {
            repeat(cancerDiseases.size) { add(PieEntry(percent, "")) }
        }

        val personalPieDataSet = PieDataSet(entries, "")
        val personalPieData = PieData(personalPieDataSet)

        // set common attribute
        val text = when {
            pRisk <= 50.0 -> "低風險"
            pRisk <= 75.0 -> "中風險"
            else -> "高風險"
        }
        initPieChart(personalPieChart, personalPieData, text)

        // set pie colors
        val chartRatio = ceil((cancerDiseases.size - 1) * pRisk / 100).toInt()
        val color = when {
            pRisk <= 50.0 -> Color.GREEN
            pRisk <= 75.0 -> Color.YELLOW
            else -> Color.RED
        }
        setPieColor(personalPieData, chartRatio, color)

        // draw charts
        personalPieChart.data = personalPieData
    }

    private fun initPieChart(pieChart: PieChart, pieData: PieData, text: String) {
        pieData.apply {
            setValueFormatter(PercentFormatter(pieChart))   // Format values as percentages
            setDrawValues(false)
        }

        // Customize the chart
        pieChart.apply {
            description.isEnabled = false
            holeRadius = 90f
            transparentCircleRadius = 45f
            legend.isEnabled = false
            centerText = text
//            centerTextSize = 16f
            rotationAngle = 0f
            animateY(1000, Easing.EaseInOutQuad)
            invalidate()
        }
    }

    private fun setPieColor(pieData: PieData, pieCount: Int, color: Int) {
        val colors = ArrayList<Int>().apply {
            repeat(cancerDiseases.size) { i ->
                add(if (i < pieCount) color else Color.LTGRAY)
            }
        }
        (pieData.dataSet as PieDataSet).colors = colors
    }

    private fun computeRisk(): Double {
        val person = Person.getInstance()
        val icd9s = person.getDiseaseICD9(cancer)

        var fw = wDiseases.filterKeys { icd9s.contains(it) }.values.sum()
        fw += bias

        val computedPRisk = sigmoid(fw) * 100
        Log.d("qwer", "pRisk: $computedPRisk")
        return computedPRisk
    }

    private fun sigmoid(x: Double): Double {
        return 1 / (1 + exp(-x))
    }

    private fun loadConfig() {
        // Load properties from assets
        val properties = Properties()
        val assetManager: AssetManager = assets

        try {
            val inputStream: InputStream = assetManager.open("disease_chart_activity.properties")
            properties.load(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val cancerLower = cancer.lowercase()
        try {
            // ===================== weights ========================
            val weightsKey = "$cancerLower.model.weights"   // e.g. bladder.model.weights
            val wDiseasesStr = properties.getProperty(weightsKey, "").replace(Regex("[()]"), "").split(",\\s*".toRegex())

            // Iterate over tuple array and populate the map
            wDiseases = if (wDiseasesStr.isNotEmpty() && wDiseasesStr[0].isNotEmpty()) {
                wDiseasesStr.toList().chunked(2).associate { (key, value) -> key to value.toDouble() }
            } else {
                emptyMap()
            }

            // Print the map
            wDiseases.forEach { (key, value) -> Log.d("qwer", "$key -> $value") }

            // ====================== bias ==============================
            val modelBiasKey = "$cancerLower.model.bias"
            val biasStr = properties.getProperty(modelBiasKey, "")

            // convert to Double
            if (biasStr.isEmpty()) throw IllegalArgumentException("Empty value found in bias.")
            bias = biasStr.toDouble()

            Log.d("qwer", "bias: $bias")

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
