package com.example.md_gbproject.viewmodel

import com.example.md_gbproject.repository.NasaData

sealed class AppState {
    data class Success(val pictureOfDayResponseData: NasaData):AppState()
    data class Error(val error:Throwable):AppState()
    data class Loading(val progress:Int?):AppState()
}