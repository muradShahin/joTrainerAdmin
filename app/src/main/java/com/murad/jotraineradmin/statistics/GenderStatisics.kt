package com.murad.jotraineradmin.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.murad.jotraineradmin.R


class GenderStatisics : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_gender_statisics, container, false)

        val aaChartView = view.findViewById<AAChartView>(R.id.aa_chart_view)

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie)
            .title("Statistics of JoTrainer App")
            .subtitle("Trainers Gender")
            .backgroundColor("#ffffff")
            .gradientColorEnable(true)
            .colorsTheme(arrayOf("#0c9674", "#7dffc0"))
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name(" Trainers Statistics ")
                    .data(arrayOf(
                        arrayOf( "Male",60) ,arrayOf("Female",40)
                    )
                    )
            ))

        aaChartView.aa_drawChartWithChartModel(aaChartModel)

        return view
    }


}