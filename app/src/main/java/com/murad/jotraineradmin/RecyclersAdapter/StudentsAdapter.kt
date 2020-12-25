package com.murad.jotraineradmin.RecyclersAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.murad.jotraineradmin.Enities.Trainees
import com.murad.jotraineradmin.R
import com.murad.jotraineradmin.TrainerDetails.Teacher_Students
import com.murad.jotraineradmin.TrainerDetails.TrainerDetails

class StudentsAdapter(val items:ArrayList<Trainees>, val fragment :Teacher_Students) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolderClass>() {


    class StudentViewHolderClass(val layout:ConstraintLayout) : RecyclerView.ViewHolder(layout){

        val studentImage=layout.findViewById<ImageView>(R.id.studentImage)
        val studentName=layout.findViewById<TextView>(R.id.studentName)
        val button=layout.findViewById<Button>(R.id.more)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolderClass {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.student_row,parent,false) as ConstraintLayout

        return StudentViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolderClass, position: Int) {
        val item=items[position]

        Glide.with(fragment.requireContext())
            .load(item.getProfileImg())
            .into(holder.studentImage)

        holder.studentName.text=item.getFname() +" "+item.getLname()

        holder.button.setOnClickListener {

            fragment.onMoreClicked(item)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
}