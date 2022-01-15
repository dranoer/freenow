package com.dranoer.freenow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dranoer.freenow.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VehicleListFragment.newInstance())
                .commitNow()
        }
    }
}