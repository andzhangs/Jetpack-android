package io.dushu.lifcycle.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.dushu.lifcycle.R

class LifeCycleActivity : AppCompatActivity() {

    private lateinit var chronometer: MyChronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometer = findViewById(R.id.chronometer)
        lifecycle.addObserver(chronometer)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(chronometer)
    }
}
