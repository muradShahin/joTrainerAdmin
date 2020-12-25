package com.murad.jotraineradmin.Login

import androidx.lifecycle.MutableLiveData

class LoginViewModel {

    private val canLogin:MutableLiveData<Boolean> =MutableLiveData<Boolean>()


    private fun checkIfCorrect(email:String , password:String){

        canLogin.value = email =="admin" && password =="admin"

    }

   fun getLoginResult(email: String,password: String) : MutableLiveData<Boolean>{
       checkIfCorrect(email,password)
       return canLogin
   }





}