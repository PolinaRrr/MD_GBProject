package com.example.md_gbproject.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.md_gbproject.utils.KEY_NASA_DOMAIN

class NasaRetrofitImpl {
    fun getRetrofit(): NasaAPI {
        val pictureOfTheDayRetrofit = Retrofit.Builder()
            .baseUrl(KEY_NASA_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return pictureOfTheDayRetrofit.create(NasaAPI::class.java)
    }
}