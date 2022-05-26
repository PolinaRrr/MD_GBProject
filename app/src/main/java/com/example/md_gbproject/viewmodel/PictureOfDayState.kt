package com.example.md_gbproject.viewmodel

import com.example.md_gbproject.repository.PictureOfDayResponseData

sealed class PictureOfDayState {
    data class Success(val pictureOfDayResponseData: PictureOfDayResponseData):PictureOfDayState()
    data class Error(val error:Throwable):PictureOfDayState()
    data class Loading(val progress:Int?):PictureOfDayState()
}