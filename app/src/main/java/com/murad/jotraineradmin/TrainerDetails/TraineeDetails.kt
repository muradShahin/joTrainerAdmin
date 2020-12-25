package com.murad.jotraineradmin.TrainerDetails

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.murad.jotraineradmin.Enities.Trainees
import com.murad.jotraineradmin.R
import kotlinx.android.synthetic.main.fragment_trainee_details.view.*
import kotlinx.android.synthetic.main.fragment_trainee_details.view.address
import kotlinx.android.synthetic.main.fragment_trainee_details.view.email
import kotlinx.android.synthetic.main.fragment_trainee_details.view.phone
import kotlinx.android.synthetic.main.fragment_trainer_details.view.*


class TraineeDetails : Fragment() , OnMapReadyCallback{


    lateinit var mapView:MapView

     lateinit var traineerDetails:Trainees
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            traineerDetails= it.getParcelable<Trainees>("trainee")!!
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_trainee_details, container, false)

        mapView=view.findViewById(R.id.mapView)

        mapView.onCreate(savedInstanceState)

       mapView.getMapAsync(this)


        initTraineeInfo(view)
        return view
    }

    private fun initTraineeInfo(view:View) {

        view.phone.text="0"+traineerDetails.getPhone()
        view.email.text=traineerDetails.getEmail()
        view.address.text=traineerDetails.getCity()
        view.birth.text=traineerDetails.getAge()

        Glide.with(requireContext())
            .load(traineerDetails.getProfileImg())
            .into(view.studentImage)
        view.studentName.text= "${traineerDetails.getFname()} ${traineerDetails.getLname()}"


        //email
        view.linearLayout4.setOnClickListener {

            sendEmail()
        }

        //phone
        view.linearLayout3.setOnClickListener {

            onCallBtnClick()
        }
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(traineerDetails.getEmail()))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        intent.putExtra(Intent.EXTRA_TEXT, "mail body")
        startActivity(Intent.createChooser(intent, ""))
    }


    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    traineerDetails.getLat()!!.toDouble(),
                    traineerDetails.getLng()!!.toDouble()
                )
            ).title("Trainee location")
        )
        val zoomLevel = 16.0f //This goes up to 21

        googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(   LatLng(
            traineerDetails.getLat()!!.toDouble(),
            traineerDetails.getLng()!!.toDouble()
        ), zoomLevel))
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
            callIntent.data = Uri.parse("tel:" + "0${traineerDetails.getPhone()}")
            requireActivity().startActivity(callIntent)
        } else {
            Toast.makeText(requireActivity(), "You don't assign permission.", Toast.LENGTH_SHORT).show()
        }
    }



}