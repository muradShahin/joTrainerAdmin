package com.murad.jotraineradmin.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
 interface AppComponent{


    open fun getRetrofit() :Retrofit

}