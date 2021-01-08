package com.murad.jotraineradmin.Home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.murad.jotraineradmin.App
import com.murad.jotraineradmin.Enities.Trainers
import com.murad.jotraineradmin.R
import com.murad.jotraineradmin.RecyclersAdapter.TeachersAdapter
import com.murad.jotraineradmin.Utils.Api
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class HomeFragment : Fragment() {

    var recyclerAdapter:TeachersAdapter?=null
    private lateinit var recyclerView: RecyclerView

    private lateinit var pd:ProgressDialog
    private lateinit var viewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)


        pd= ProgressDialog(requireContext())
        viewModel=HomeViewModel(requireActivity())

        recyclerAdapter= TeachersAdapter(this, ArrayList<Trainers>())
        recyclerView=view.findViewById(R.id.recycler)
        recyclerView.layoutManager=LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )


        view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                getDataOnSearch(p0!!)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //   getDataOnSearch(p0!!)
                return true
            }


        })

        view.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                getData()

                return true
            }


        })

        view.floatingActionButton.setOnClickListener {

            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToStatistcsHome())
        }





        getData()
        return view
    }

    fun getData(){
        showProgressDialog()
        viewModel.getTrainersList().observe(viewLifecycleOwner, object : Observer<List<Trainers>> {
            override fun onChanged(t: List<Trainers>?) {

                hideProgressDialog()
                recyclerAdapter?.updateList(t!!)
                recyclerView.adapter = recyclerAdapter


            }


        })


    }

    fun getDataOnSearch(query: String){
        showProgressDialog()
        viewModel.getDataFromSeach(query)
        viewModel.getSearchResult().observe(viewLifecycleOwner, object : Observer<List<Trainers>> {
            override fun onChanged(list: List<Trainers>?) {
                hideProgressDialog()

                if (list!!.isNotEmpty()) {
                    Log.i("testSearch", list!![0].getFname().toString())
                    recyclerAdapter?.updateList(list!!)
                    recyclerView.adapter = recyclerAdapter
                } else {
                    Toast.makeText(requireContext(), "No Result", Toast.LENGTH_SHORT).show()
                }

            }


        })

    }



     fun showTrainerDetails(item: Trainers){

        view?.findNavController()?.navigate(
            HomeFragmentDirections.actionHomeFragmentToTrainerDetails(
                item
            )
        )

    }

    private fun showProgressDialog(){
        pd.setMessage("Loading..")
        pd.show()
    }

    private fun hideProgressDialog(){
        pd.hide()
    }


}