package com.example.md_gbproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.md_gbproject.BuildConfig
import com.example.md_gbproject.repository.PictureOfDayResponseData
import com.example.md_gbproject.repository.PictureOfDayRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfDayViewModel(
    private val liveData: MutableLiveData<PictureOfDayState> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfDayRetrofitImpl = PictureOfDayRetrofitImpl()
) : ViewModel() {


    fun getLiveData(): LiveData<PictureOfDayState> {
        return liveData
    }
    private val callback = object : Callback<PictureOfDayResponseData>{
        override fun onResponse(
            call: Call<PictureOfDayResponseData>,
            response: Response<PictureOfDayResponseData>
        ) {
            if (response.isSuccessful){
                response.body()?.let {
                    liveData.postValue(PictureOfDayState.Success(it))

                }
            }else{
                liveData.postValue(PictureOfDayState.Error(Exception()))
            }
        }

        override fun onFailure(call: Call<PictureOfDayResponseData>, t: Throwable) {
            //TODO
        }
    }

    fun sendRequest(){
        liveData.postValue(PictureOfDayState.Loading(null))
        //TODO
        pictureOfTheDayRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}