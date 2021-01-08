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


class TraineesGender : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_trainees_gender, container, false)

        val aaChartView = view.findViewById<AAChartView>(R.id.aa_chart_view)

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie)
            .title("Statistics of JoTrainer App")
            .subtitle("Trainees Gender")
            .backgroundColor("#ffffff")
            .colorsTheme(arrayOf("#6ab7ff", "#bb002f"))
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name(" Trainees Statistics ")
                    .data(arrayOf(
                        arrayOf( "Male",30) ,arrayOf("Female",70)
                    )
                    )
            ))

        aaChartView.aa_drawChartWithChartModel(aaChartModel)

        return view
    }


}