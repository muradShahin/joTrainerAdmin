package com.murad.jotraineradmin.TrainerDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.murad.jotraineradmin.Enities.Trainees
import com.murad.jotraineradmin.R
import com.murad.jotraineradmin.RecyclersAdapter.StudentsAdapter
import kotlinx.android.synthetic.main.fragment_teacher__students.view.*


class Teacher_Students : Fragment() {


    private lateinit var listOfStudents:Array<Trainees>
    private lateinit var StudentsRecyclerAdapter:StudentsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            listOfStudents= it!!.getParcelableArray("trainees") as Array<Trainees>

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_teacher__students, container, false)

        init(view)
        Log.i("testArray","${listOfStudents.size}")

        return view
    }

    private fun init(view:View) {


        val traineesList=ArrayList<Trainees>()

        for (i in listOfStudents.indices){

            Log.i("indexTest",listOfStudents[i].getFname().toString())
            traineesList.add(listOfStudents[i])
        }


        StudentsRecyclerAdapter= StudentsAdapter(traineesList,this)

        view.recStudents.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        view.recStudents.adapter=StudentsRecyclerAdapter



    }

    fun onMoreClicked(trainee: Trainees){

        view?.findNavController()?.navigate(Teacher_StudentsDirections.actionTeacherStudentsToTraineeDetails(trainee))
    }


}