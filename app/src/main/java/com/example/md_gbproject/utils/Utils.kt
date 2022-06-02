package com.example.md_gbproject.utils




import java.util.*

const val KEY_APOD_PATH = "planetary/apod"
const val KEY_NASA_DOMAIN = "https://api.nasa.gov/"
const val WIKI_DOMAIN = "https://en.wikipedia.org/wiki/"
const val TODAY = "today"
const val YESTERDAY = "yesterday"
const val DAY_BEFORE_YESTERDAY = "dayBeforeYesterday"
const val SURPRISE_IMAGE="https://cs14.pikabu.ru/post_img/2022/05/29/8/1653832275156942916.gif"
const val MEDIA_TYPE_IMAGE ="image"
const val MEDIA_TYPE_VIDEO ="video"
const val CHOSEN_THEME = "chosen theme"
const val LOCAL_SP = "local settings theme"


class Utils {

    companion object{
        fun getData(dayType:String): String{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            var day = 0
            when(dayType){
                TODAY->{
                    day = c.get(Calendar.DAY_OF_MONTH)
                }
                YESTERDAY->{
                    day = c.get(Calendar.DAY_OF_MONTH)-1
                }
                DAY_BEFORE_YESTERDAY->{
                    day = c.get(Calendar.DAY_OF_MONTH)-2
                }
            }
            return  "$year-$month-$day"
        }
    }
}