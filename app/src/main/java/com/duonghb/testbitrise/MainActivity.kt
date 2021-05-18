package com.duonghb.testbitrise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.duonghb.testbitrise.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val fragment: Fragment
        get() = HomeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
