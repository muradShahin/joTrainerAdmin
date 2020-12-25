package com.murad.jotraineradmin.TrainerDetails

import android.app.ProgressDialog
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
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import java.util.*
import javax.xml.transform.ErrorListener
import javax.xml.transform.TransformerException
import kotlin.collections.ArrayList
import kotlin.math.ln

class TrainerDetailsViewModel(val context: Context)  {


    private var isApproved=MutableLiveData<Boolean>()
    private var isStoped=MutableLiveData<Boolean>()
    private var traineesLiveList=MutableLiveData<List<Trainees>>()

    private lateinit var pd:ProgressDialog

    private val apiName="approverTrainer.php"


    fun approveTrainerOnServer(item: Trainers){


        pd= ProgressDialog(context)
        pd.setMessage("approving ....")
        pd.show()

        val queue: RequestQueue = Volley.newRequestQueue(context)
        val url: String = Config.url+ apiName


        val req: StringRequest =
            object : StringRequest(Request.Method.POST, url, object : Response.Listener<String?> {
                override fun onResponse(s: String?) {

                    // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    pd.dismiss()
                    try {
                        val o = JSONObject(s)
                        val result = o.getString("result")


                        if (result == "1")
                            isApproved.value = true
                        else
                            isApproved.value = false



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
                    param["id"] = item.getId().toString()
                    param["approve"]="true"



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

    fun stopTrainerOnServer(item: Trainers){



        val queue: RequestQueue = Volley.newRequestQueue(context)
        val url: String = Config.url+ apiName


        val req: StringRequest =
            object : StringRequest(Request.Method.POST, url, object : Response.Listener<String?> {
                override fun onResponse(s: String?) {

                    // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    try {
                        val o = JSONObject(s)
                        val result = o.getString("result")

                        if (result == "1")
                            isStoped.value = true
                        else
                            isStoped.value = false


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
                    param["id"] = item.getId().toString()
                    param["approve"]="false"



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


    fun getTrainerStudents(email: String){

        val appComponent=(context.applicationContext as App).getAppComponent()
        val retrofit=appComponent.getRetrofit()

        val api=retrofit.create(Api::class.java)
        api.getStudents(email).enqueue(object : retrofit2.Callback<Any> {
            override fun onResponse(call: Call<Any>, response: retrofit2.Response<Any>) {
                val gson = Gson()

                val jsonObject = JSONObject(gson.toJson(response.body()))

                val jsonArray = jsonObject.getJSONArray("result")



                Log.i("testArrayOfJson",response.body().toString())

                val traineesList=ArrayList<Trainees>()
                for (i in 0 until jsonArray.length()) {


                    val objectOfIndex = jsonArray.getJSONObject(i);

                    val traineeDetails= Trainees()

                    Log.i("testmURAD",objectOfIndex.getString("student_email"))

                    val id: String = objectOfIndex.getString("contract_id")
                    val student_email: String = objectOfIndex.getString("student_email")
                    val teacher_email: String = objectOfIndex.getString("teacher_email")
                    val date: String = objectOfIndex.getString("date")
                    val student_name: String = objectOfIndex.getString("student_name")
                    val teacher_name: String = objectOfIndex.getString("teacher_name")
                    val n_lessons: Int = objectOfIndex.getInt("n_lessons")
                    val n_left: Int = objectOfIndex.getInt("n_lessonsLeft")

                    traineeDetails.setEmail(student_email)
                    traineeDetails.setLessons(n_lessons.toString())
                    traineeDetails.setLessons_left(n_left)
                    traineeDetails.setTeacherName(teacher_name)
                    traineeDetails.setFname(student_name)



                    api.getStudentDetails(student_email).enqueue(object : retrofit2.Callback<Any> {
                        override fun onResponse(
                            call: Call<Any>,
                            response: retrofit2.Response<Any>
                        ) {
                            val gson = Gson()
                            val jsonDetailsArray =
                                (JSONObject(gson.toJson(response.body()))).getJSONArray(
                                    "result"
                                )

                            for (i in 0 until jsonDetailsArray.length()) {

                                val jsonObjectAtIndex = jsonDetailsArray.getJSONObject(i)


                                if(student_email.equals(jsonObjectAtIndex.getString("email"))) {
                                    val id: Int = jsonObjectAtIndex.getInt("id")
                                    val email: String = jsonObjectAtIndex.getString("email")
                                    val fname: String = jsonObjectAtIndex.getString("fname")
                                    val lname: String = jsonObjectAtIndex.getString("lname")
                                    val phone: String = jsonObjectAtIndex.getString("phone")
                                    val city: String = jsonObjectAtIndex.getString("city")
                                    val age: String = jsonObjectAtIndex.getString("age")
                                    val lat: String = jsonObjectAtIndex.getString("lat")
                                    val lng: String = jsonObjectAtIndex.getString("lng")
                                    val rate: Int = jsonObjectAtIndex.getInt("rate")
                                    val profileImg: String =
                                        jsonObjectAtIndex.getString("profileImg")

                                    traineeDetails.setFname(fname)
                                    traineeDetails.setLname(lname)
                                    traineeDetails.setPhone(phone)
                                    traineeDetails.setCity(city)
                                    traineeDetails.setAge(age)
                                    traineeDetails.setLat(lat)
                                    traineeDetails.setLng(lng)
                                    traineeDetails.setRate(rate)
                                    traineeDetails.setProfileImg(profileImg)

                                }


                            }






                        }

                        override fun onFailure(call: Call<Any>, t: Throwable) {

                            Log.i("errorFetchingstuetails", t.message.toString())
                        }

                    })
                    traineesList.add(traineeDetails)
                }

                traineesLiveList.value=traineesList

            }

            override fun onFailure(call: Call<Any>, t: Throwable) {

                Log.i("errorFetchingStudents", t.message.toString())
            }


        })

    }

    fun getTeacherStudents(email: String) :LiveData<List<Trainees>>{
        getTrainerStudents(email)
        return traineesLiveList

    }


    fun getIsApproved():LiveData<Boolean>{
        return isApproved
    }

    fun getIsStoped():LiveData<Boolean>{
        return isStoped
    }

}