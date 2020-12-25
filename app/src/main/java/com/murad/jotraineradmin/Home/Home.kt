package com.murad.jotraineradmin.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.murad.jotraineradmin.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
    }
}