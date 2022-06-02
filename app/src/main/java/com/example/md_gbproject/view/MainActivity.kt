package com.example.md_gbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.md_gbproject.R
import com.example.md_gbproject.utils.CHOSEN_THEME
import com.example.md_gbproject.utils.LOCAL_SP
import com.example.md_gbproject.view.picture.PictureOfDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getIdTheme(getCurrentIdTheme()))
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfDayFragment.newInstance()).commit()
        }
    }

    private fun getIdTheme(idTheme: Int): Int {
        return idTheme
    }
    private fun getCurrentIdTheme(): Int {
        val sharedPreferences = getSharedPreferences(LOCAL_SP, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(CHOSEN_THEME, -1)
    }

}