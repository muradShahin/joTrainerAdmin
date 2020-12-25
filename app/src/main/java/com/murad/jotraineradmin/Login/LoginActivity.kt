package com.murad.jotraineradmin.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.murad.jotraineradmin.Home.Home
import com.murad.jotraineradmin.MainActivity
import com.murad.jotraineradmin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {



    private var viewModel:LoginViewModel ? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        init()



    }

    private fun init() {
        viewModel= LoginViewModel()


        login.setOnClickListener {

            viewModel!!.getLoginResult(mail.text.toString(),password.text.toString()).observe(this,object :Observer<Boolean>{
                override fun onChanged(t: Boolean?) {

                    if(t!!){
                        val intent=Intent(this@LoginActivity,Home::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity,"something went wrong",Toast.LENGTH_SHORT).show()
                    }


                }


            })

        }


    }
}