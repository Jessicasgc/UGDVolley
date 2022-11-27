package com.example.ugdnyakawan.Tab

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ugdnyakawan.databinding.ActivityRegistrasiBinding
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.area
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.viz.VizContainerView

class Chart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(JumlahPesanan(this))
    }
}

class JumlahPesanan(context: Context) : VizContainerView(context) {

    private val chart: Chart<PopCount> = chart(canPop) {
        size = Size(vizSize, vizSize)
        title = "Jumlah Pesanan Pengguna Dalam Setahun"

        // Create a discrete dimension for the year of the census
        val month = discrete({ domain.month})

        // Create a continuous numeric dimension for the population
        val jumlah = quantitative({ domain.jumlah}) {
            name = "Jumlah Pesanan setiap bulannya"
        }

        // Using a discrete dimension for the X-axis and a continuous one for the Y-axis
        area(month, jumlah)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * h / w)
    }
}

const val vizSize = 500.0

data class PopCount(val month: Int, val jumlah: Double)

val canPop = listOf(
    PopCount(1, 50.00),
    PopCount(2, 80.00),
    PopCount(3, 10.00),
    PopCount(4, 7.00),
    PopCount(5, 2.00),
    PopCount(6, 20.00),
    PopCount(7, 5.00),
    PopCount(8, 10.00),
    PopCount(9, 30.00),
    PopCount(10,70.00),
    PopCount(11, 50.00),
    PopCount(12, 20.00),

)