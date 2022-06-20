package com.example.md_gbproject.repository

import com.example.md_gbproject.utils.KEY_APOD_PATH
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaAPI {
    @GET(KEY_APOD_PATH)
    fun getPictureOfTheDay(@Query("api_key") apiKey: String)
    : Call<NasaData>

    @GET(KEY_APOD_PATH)
    fun getPictureOfTheDay(@Query("api_key") apiKey: String,@Query("date") date:String)
            : Call<NasaData>
}