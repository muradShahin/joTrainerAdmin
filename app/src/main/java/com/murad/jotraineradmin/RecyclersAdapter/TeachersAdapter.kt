package com.murad.jotraineradmin.RecyclersAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.murad.jotraineradmin.Enities.Trainers
import com.murad.jotraineradmin.Home.HomeFragment
import com.murad.jotraineradmin.R

class TeachersAdapter(val fragment:HomeFragment ,var items:List<Trainers>) :RecyclerView.Adapter<TeachersAdapter.ViewHolderTeacher>() {


    class ViewHolderTeacher(val layout:ConstraintLayout) :RecyclerView.ViewHolder(layout){

        val trainerImage=layout.findViewById<ImageView>(R.id.trainerImg)
        val trainerName=layout.findViewById<TextView>(R.id.trainerName)
        val status=layout.findViewById<TextView>(R.id.status)
        val moreBtn=layout.findViewById<Button>(R.id.moreBtn)


    }

    fun updateList(newItems:List<Trainers>){
        this.items=newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTeacher {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.trainers_row,parent,false) as ConstraintLayout

        return ViewHolderTeacher(view)
    }

    override fun onBindViewHolder(holder: ViewHolderTeacher, position: Int) {


        if(items[position].getProfileImg() !=null){
            Glide.with(fragment.requireContext())
                .load(items[position].getProfileImg())
                .into(holder.trainerImage)
        }

        holder.trainerName.text=items[position].getFname() + " "+items[position].getLname()

        if(items[position].getIsApproved() =="true"){
            holder.status.text="is Approved"
        }else{
            holder.status.text="not Approved yet"
        }

        holder.moreBtn.setOnClickListener {

            fragment.showTrainerDetails(items[position])
        }



    }

    override fun getItemCount(): Int {

        return items.size
    }
}