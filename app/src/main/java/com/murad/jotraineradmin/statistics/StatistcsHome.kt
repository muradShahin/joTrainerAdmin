package com.murad.jotraineradmin.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.murad.jotraineradmin.R
import kotlinx.android.synthetic.main.fragment_statistcs_home.view.*


class StatistcsHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_statistcs_home, container, false)

        view.activation.setOnClickListener {

            view.findNavController().navigate(StatistcsHomeDirections.actionStatistcsHomeToStatics())
        }

        view.male_femal.setOnClickListener {

            view.findNavController().navigate(StatistcsHomeDirections.actionStatistcsHomeToGenderStatisics())

        }

        view.trainees.setOnClickListener {
            view.findNavController().navigate(StatistcsHomeDirections.actionStatistcsHomeToTraineesGender())

        }

        return view
    }


}