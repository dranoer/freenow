package com.dranoer.freenow.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dranoer.freenow.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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