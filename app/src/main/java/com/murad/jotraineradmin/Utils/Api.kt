package com.murad.jotraineradmin.Utils

import retrofit2.Call
import retrofit2.http.*

interface Api {



    @FormUrlEncoded
    @POST("getAllUsers.php")
    open fun getData(@Field("role") role:String): Call<Any>

    @FormUrlEncoded
    @POST("getTeacherStudents.php")
    open fun getStudents(@Field("email") email:String) :Call<Any>

    @FormUrlEncoded
    @POST("getStudentData.php")
    open fun getStudentDetails(@Field("email") email:String) :Call<Any>


}