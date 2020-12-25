package com.murad.jotraineradmin.Home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.murad.jotraineradmin.App
import com.murad.jotraineradmin.Enities.Trainees
import com.murad.jotraineradmin.Enities.Trainers
import com.murad.jotraineradmin.Utils.Api
import com.murad.jotraineradmin.Utils.Config
import com.murad.jotraineradmin.di.AppComponent
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import java.util.*
import javax.xml.transform.ErrorListener
import javax.xml.transform.TransformerException
import kotlin.collections.ArrayList

open class HomeViewModel(val context: Context) {


    private val trainersList=MutableLiveData<List<Trainers>>()
    private val trainersListSearch=MutableLiveData<List<Trainers>>()


    private var listOfTrainers=ArrayList<Trainers>()
    val searchTrainer=ArrayList<Trainers>()

   private fun getAllTrainers() {



       val queue: RequestQueue = Volley.newRequestQueue(context)
        val url: String = Config.url+ "getAllUsers.php"


        val req: StringRequest =
            object : StringRequest(Request.Method.POST, url, object : Response.Listener<String?> {
                override fun onResponse(s: String?) {

                    // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    try {
                        val o = JSONObject(s)
                        val arr = o.getJSONArray("result")

                        val trainersArray = ArrayList<Trainers>()
                        for (i in 0 until arr.length()) {
                            val x = arr.getJSONObject(i)
                            val id = x.getInt("id")
                            val email = x.getString("email")
                            val fname = x.getString("fname")
                            val lname = x.getString("lname")
                            // val password = x.getString("password")
                            val phone = x.getString("phone")
                            val role = x.getString("role")
                            val city = x.getString("city")
                            val age = x.getString("age")
                            val exp = x.getString("exp")
                            //  val office = x.getString("office")
                            val carType = x.getString("carType")
                            val carImg = x.getString("carImg")
                            val profileImg = x.getString("profileImg")
                            val rate = x.getString("rate")
                            val isApproved = x.getString("isApproved")
                            val lat = x.getString("lat")
                            val lng = x.getString("lng")


                            val trainer = Trainers()
                            trainer.setEmail(email)
                            trainer.setFname(fname)
                            trainer.setLname(lname)
                            trainer.setAge(age)
                            trainer.setCarType(carType)
                            trainer.setCity(city)
                            trainer.setExp(exp)
                            trainer.setLat(lat)
                            trainer.setLng(lng)
                            trainer.setRate(rate)
                            trainer.setProfileImg(profileImg)
                            trainer.setCarImg(carImg)
                            trainer.setPhone(phone)
                            trainer.setId(id)
                            trainer.setIsApproved(isApproved)

                            trainersArray.add(trainer)


                        }

                        listOfTrainers = trainersArray
                        trainersList.value = trainersArray

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }, object : ErrorListener, Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                }

                override fun warning(p0: TransformerException?) {
                }

                override fun error(p0: TransformerException?) {
                }

                override fun fatalError(p0: TransformerException?) {
                }

            }) {
                override fun getParams(): MutableMap<String, String> {
                    val param: MutableMap<String, String> = HashMap()
                    param["role"] = "teacher"



                    return param
                }
            }
        req.setRetryPolicy(
            DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        )
        queue.add(req)


    }

    fun getTrainersList():LiveData<List<Trainers>>{
        getAllTrainers2()
        return  trainersList
    }

    fun getDataFromSeach(query: String){
        searchTrainer.clear()
        for (i in listOfTrainers){
            if(query.contains("07")) {
                var trainerPhone = "0" + i.getPhone()
                if (query == trainerPhone) {
                    searchTrainer.add(i)

                }
            }else{

                if(query.toLowerCase()==i.getFname()?.toLowerCase()?.trim()){
                    searchTrainer.add(i)
                }else if(query.toLowerCase() ==i.getLname()?.toLowerCase()?.trim()){
                    searchTrainer.add(i)
                }

            }

        }

        trainersListSearch.value=searchTrainer
    }

    fun getAllTrainers2(){

        val retrofit=(context.applicationContext as App).getAppComponent().getRetrofit()

        val api=retrofit.create(Api::class.java)

        val getTrainers=api.getData("teacher")


        getTrainers?.enqueue(object : retrofit2.Callback<Any> {
            override fun onResponse(
                call: Call<Any>,
                response: retrofit2.Response<Any>
            ) {

                val gson = Gson()
                val json = gson.toJson(response.body())

                val jsonObject=JSONObject(json)
                val arrayOfObjects=jsonObject.getJSONArray("result")

                val trainerList=ArrayList<Trainers>()
                for (i in 0 until arrayOfObjects.length()){

                    val x=arrayOfObjects.getJSONObject(i)
                    val id = x.getInt("id")
                    val email = x.getString("email")
                    val fname = x.getString("fname")
                    val lname = x.getString("lname")
                    // val password = x.getString("password")
                    val phone = x.getString("phone")
                    val role = x.getString("role")
                    val city = x.getString("city")
                    val age = x.getString("age")
                    val exp = x.getString("exp")
                    //  val office = x.getString("office")
                    val carType = x.getString("carType")
                    val carImg = x.getString("carImg")
                    val profileImg = x.getString("profileImg")
                    val rate = x.getString("rate")
                    val isApproved = x.getString("isApproved")
                    val lat = x.getString("lat")
                    val lng = x.getString("lng")


                    val trainer = Trainers()
                    trainer.setEmail(email)
                    trainer.setFname(fname)
                    trainer.setLname(lname)
                    trainer.setAge(age)
                    trainer.setCarType(carType)
                    trainer.setCity(city)
                    trainer.setExp(exp)
                    trainer.setLat(lat)
                    trainer.setLng(lng)
                    trainer.setRate(rate)
                    trainer.setProfileImg(profileImg)
                    trainer.setCarImg(carImg)
                    trainer.setPhone(phone)
                    trainer.setId(id)
                    trainer.setIsApproved(isApproved)

                    trainerList.add(trainer)


                }

                trainersList.value=trainerList;

                Log.i("testRetrofit", json)


            }

            override fun onFailure(call: Call<Any>, t: Throwable) {

                Log.i("testRetrofitERR", t.toString())

            }


        })
    }

    fun getSearchResult():LiveData<List<Trainers>>{

        return trainersListSearch

    }

    }
