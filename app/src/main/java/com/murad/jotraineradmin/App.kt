package com.murad.jotraineradmin

import android.app.Application
import com.murad.jotraineradmin.di.AppComponent
import com.murad.jotraineradmin.di.DaggerAppComponent

class App  :Application() {



    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

    }



    fun getAppComponent(): AppComponent {
        return appComponent
    }

}