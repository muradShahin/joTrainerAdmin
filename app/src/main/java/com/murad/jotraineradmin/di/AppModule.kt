package com.murad.jotraineradmin.di

import com.google.gson.GsonBuilder
import com.murad.jotraineradmin.Utils.Config
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun initRetrofit():Retrofit{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit=Retrofit.Builder()
            .baseUrl(Config.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

}