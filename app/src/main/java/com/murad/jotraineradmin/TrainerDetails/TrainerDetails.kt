package com.murad.jotraineradmin.TrainerDetails

import android.Manifest
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.murad.jotraineradmin.Enities.Trainees
import com.murad.jotraineradmin.Enities.Trainers
import com.murad.jotraineradmin.R
import kotlinx.android.synthetic.main.car_details_dialog.*
import kotlinx.android.synthetic.main.certificate.*
import kotlinx.android.synthetic.main.fragment_trainer_details.view.*
import java.util.*
import kotlin.collections.ArrayList


class TrainerDetails : Fragment()  {

    private lateinit var trainer:Trainers
    private  val TAG = "TrainerDetails"

    private lateinit var viewModel:TrainerDetailsViewModel

    private lateinit var pd:ProgressDialog

    private lateinit var listOfTrainees:Array<Trainees>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_trainer_details, container, false)

        viewModel=TrainerDetailsViewModel(requireContext())
        arguments.let {
            trainer= it!!.getParcelable("trainer")!!
        }



        init(view)



    return view
    }

    private fun init(view: View){

        pd= ProgressDialog(requireContext())
        view.trainerName.text=trainer.getFname() +" "+trainer.getLname()
        view.email.text=trainer.getEmail()
        view.phone.text="0"+trainer.getPhone()

        Glide.with(requireContext())
            .load(trainer.getProfileImg())
            .into(view.trainerImage)

        view.age.text=trainer.getAge()

        if(trainer.getIsApproved()=="true") {
            view.stopBtn.visibility = View.VISIBLE
            view.approveBtn.visibility=View.GONE
        }
        else{
            view.stopBtn.visibility = View.GONE
            view.approveBtn.visibility=View.VISIBLE
        }





        getLocationDetails(view)






        view.approveBtn.setOnClickListener {

            approveTrainer()
        }

        view.stopBtn.setOnClickListener {
            stopTrainer()
        }


        view.showCarBtn.setOnClickListener {

            showDetailsCarDialog()



        }


        view.phoneLay.setOnClickListener {
           onCallBtnClick()
        }

        view.emailLay.setOnClickListener {
            composeEmail()
        }

        view.certCard.setOnClickListener {
            showCertificate()
        }





        getTeacherStudent()


        view.studentsCard.setOnClickListener {
          view.findNavController().navigate(TrainerDetailsDirections.actionTrainerDetailsToTeacherStudents(listOfTrainees))
        }


    }

    private fun showCertificate() {
        val dialog=Dialog(requireContext())
        dialog.setContentView(R.layout.certificate)

        if(trainer.getCertificate() !=null) {
            Glide.with(requireActivity())
                .load(trainer.getCertificate())
                .into(dialog.certImage)
        }else
            Toast.makeText(requireContext(),"No Certification Found !",Toast.LENGTH_LONG).show()


        dialog.window?.setBackgroundDrawableResource(R.color.transperant)

        dialog.show()




    }

    private fun getTeacherStudent() {
        val pd=ProgressDialog(requireContext())
        pd.setMessage("Loading ....")
        pd.show()
        viewModel.getTeacherStudents(trainer.getEmail()!!).observe(viewLifecycleOwner,object :Observer<List<Trainees>>{
            override fun onChanged(t: List<Trainees>?) {

                pd.dismiss()

                listOfTrainees= Array(t!!.size){Trainees()}

                for (i in t.indices){
                    Log.i("testTrainees",t[i].getFname().toString())
                    listOfTrainees[i]=t[i]
                }


            }
        })
    }

    private fun showDetailsCarDialog() {

        val dialog=Dialog(requireContext())
        dialog.setContentView(R.layout.car_details_dialog)

        dialog.carDetails.text=trainer.getCarType()

        Glide.with(requireContext())
            .load(trainer.getCarImg())
            .into(dialog.carImage)

        dialog.window?.setBackgroundDrawableResource(R.color.transperant)
        dialog.show()


    }


    private fun getLocationDetails(view: View) {

        Log.i(TAG, trainer.getLat()!! + " " + trainer.getLat()!!)
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(requireContext(), Locale.getDefault())


        try {
            addresses = geocoder.getFromLocation(
                trainer.getLat()?.toDouble()!!,
                trainer.getLng()?.toDouble()!!,
                1
            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5


            var address: String = ""

            if (addresses[0].getAddressLine(0) != null)
                address = addresses[0].getAddressLine(0)

            /* val city: String = addresses[0].getLocality()
        val state: String = addresses[0].getAdminArea()
        val country: String = addresses[0].getCountryName()
        val postalCode: String = addresses[0].getPostalCode()
        val knownName: String = addresses[0].getFeatureName()*/

            view.address.text = address
        }catch (e :Exception){
            Log.i("err",e.toString())
        }

    }

    private fun approveTrainer() {


        if(trainer.getIsApproved()=="false"){
            viewModel.approveTrainerOnServer(trainer)
        }

        viewModel.getIsApproved().observe(viewLifecycleOwner, object : Observer<Boolean> {
            override fun onChanged(result: Boolean?) {



                if (result!!) {
                    view?.stopBtn?.visibility = View.VISIBLE
                    view?.approveBtn?.visibility = View.GONE
                } else {
                    Toast.makeText(requireContext(), "Something went wrong !!", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        })

    }

    private fun stopTrainer(){
        viewModel.stopTrainerOnServer(trainer)
        viewModel.getIsStoped().observe(viewLifecycleOwner, object : Observer<Boolean> {
            override fun onChanged(result: Boolean?) {

                if (result!!) {
                    Toast.makeText(
                        requireContext(),
                        "trainer account has been stopped",
                        Toast.LENGTH_SHORT
                    ).show()
                    view?.stopBtn?.visibility = View.GONE
                    view?.approveBtn?.visibility = View.VISIBLE
                }
            }

        })
    }

    fun composeEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(trainer.getEmail()))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        intent.putExtra(Intent.EXTRA_TEXT, "mail body")
        startActivity(Intent.createChooser(intent, ""))
    }


    private fun onCallBtnClick() {
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall()
        } else {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                phoneCall()
            } else {
                val PERMISSIONS_STORAGE = arrayOf<String>(Manifest.permission.CALL_PHONE)
                //Asking request Permissions
                ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS_STORAGE, 9)
            }
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        var permissionGranted = false
        when (requestCode) {
            9 -> permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (permissionGranted) {
            phoneCall()
        } else {
            Toast.makeText(requireActivity(), "You don't assign permission.", Toast.LENGTH_SHORT).show()
        }


    }

    private fun phoneCall() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + "0${trainer.getPhone()}")
            requireActivity().startActivity(callIntent)
        } else {
            Toast.makeText(requireActivity(), "You don't assign permission.", Toast.LENGTH_SHORT).show()
        }
    }



}