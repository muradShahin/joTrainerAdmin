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
import com.murad.jotraineradmin.Enities.Trainees
import com.murad.jotraineradmin.Enities.statics
import com.murad.jotraineradmin.R


class Statics : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_statics, container, false)

        val aaChartView = view.findViewById<AAChartView>(R.id.aa_chart_view)


        val arrayList:ArrayList<statics> =java.util.ArrayList()


        arrayList.add(statics("Acive Trainers",10))
        arrayList.add(statics("inActive Trainers",20))
        arrayList.add(statics("Male Trainers",20))
        arrayList.add(statics("Female Trainers",20))



        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie)
            .title("Statistics of JoTrainer App")
            .subtitle("Trainers ")
            .backgroundColor("#ffffff")
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name(" Trainers Statistics ")
                    .data(arrayOf(
                        arrayOf( "Active",100) ,arrayOf("InActive",50)
                    )
                    )
            ))

        aaChartView.aa_drawChartWithChartModel(aaChartModel)

        return  view
    }


}