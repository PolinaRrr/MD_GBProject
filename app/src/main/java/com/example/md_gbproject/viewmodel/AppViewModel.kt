package com.example.md_gbproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.md_gbproject.BuildConfig
import com.example.md_gbproject.repository.NasaData
import com.example.md_gbproject.repository.NasaRetrofitImpl
import com.example.md_gbproject.utils.DAY_BEFORE_YESTERDAY
import com.example.md_gbproject.utils.Utils
import com.example.md_gbproject.utils.YESTERDAY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val nasaRetrofitImpl: NasaRetrofitImpl = NasaRetrofitImpl()
) : ViewModel() {


    fun getLiveData(): LiveData<AppState> {
        return liveData
    }
    private val callback = object : Callback<NasaData>{
        override fun onResponse(
            call: Call<NasaData>,
            response: Response<NasaData>
        ) {
            if (response.isSuccessful){
                Log.d("@@@","Success ${response.body()}")
                response.body()?.let {
                    liveData.postValue(AppState.Success(it))

                }
            }else{
                liveData.postValue(AppState.Error(Exception()))

                Log.d("@@@","Error ")
            }
        }

        override fun onFailure(call: Call<NasaData>, t: Throwable) {
            Log.d("@@@","Error $t")
        }
    }

    //отправка за сегодня
    fun sendRequest(){
        liveData.postValue(AppState.Loading(null))
        //TODO
        nasaRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    Log.d("@@@","${nasaRetrofitImpl.getRetrofit()}")
    }

    //отправка за вчера
    fun sendRequestForYesterday(){
        liveData.postValue(AppState.Loading(null))
        nasaRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY,Utils.getData(YESTERDAY)).enqueue(callback)

    }

    //отправка за позавчера
    fun sendRequestForBeforeYesterday(){
        liveData.postValue(AppState.Loading(null))
        nasaRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY,Utils.getData(DAY_BEFORE_YESTERDAY)).enqueue(callback)
    }

}