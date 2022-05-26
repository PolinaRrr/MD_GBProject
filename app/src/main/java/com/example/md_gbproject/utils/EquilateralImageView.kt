package com.example.md_gbproject.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

const val KEY_APOD_PATH = "planetary/apod"
const val KEY_NASA_DOMAIN = "https://api.nasa.gov/"
const val WIKI_DOMAIN = "https://en.wikipedia.org/wiki/"

class EquilateralImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}