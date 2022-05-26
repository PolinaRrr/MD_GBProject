package com.example.md_gbproject.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.md_gbproject.utils.KEY_APOD_PATH


interface PictureOfDayAPI {
    @GET(KEY_APOD_PATH)
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PictureOfDayResponseData>
}