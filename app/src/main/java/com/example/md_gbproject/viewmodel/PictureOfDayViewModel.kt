package com.example.md_gbproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.md_gbproject.BuildConfig
import com.example.md_gbproject.repository.PictureOfDayResponseData
import com.example.md_gbproject.repository.PictureOfDayRetrofitImpl
import com.example.md_gbproject.utils.DAY_BEFORE_YESTERDAY
import com.example.md_gbproject.utils.Utils
import com.example.md_gbproject.utils.YESTERDAY
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
                Log.d("@@@","Success ${response.body()}")
                response.body()?.let {
                    liveData.postValue(PictureOfDayState.Success(it))

                }
            }else{
                liveData.postValue(PictureOfDayState.Error(Exception()))

                Log.d("@@@","Error ")
            }
        }

        override fun onFailure(call: Call<PictureOfDayResponseData>, t: Throwable) {
            Log.d("@@@","Error $t")
        }
    }

    //отправка за сегодня
    fun sendRequest(){
        liveData.postValue(PictureOfDayState.Loading(null))
        //TODO
        pictureOfTheDayRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    Log.d("@@@","${pictureOfTheDayRetrofitImpl.getRetrofit()}")
    }

    //отправка за вчера
    fun sendRequestForYesterday(){
        liveData.postValue(PictureOfDayState.Loading(null))
        pictureOfTheDayRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY,Utils.getData(YESTERDAY)).enqueue(callback)

    }

    //отправка за позавчера
    fun sendRequestForBeforeYesterday(){
        liveData.postValue(PictureOfDayState.Loading(null))
        pictureOfTheDayRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY,Utils.getData(DAY_BEFORE_YESTERDAY)).enqueue(callback)
    }

}