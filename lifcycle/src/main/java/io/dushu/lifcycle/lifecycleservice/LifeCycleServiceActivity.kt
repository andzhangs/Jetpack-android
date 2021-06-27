package io.dushu.lifcycle.lifecycleservice

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import io.dushu.lifcycle.R
import kotlinx.android.synthetic.main.activity_life_cycle_service.*

class LifeCycleServiceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_service)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            200
        )
    }

    private var mIntent: Intent? = null

    fun startGpsLocation(view: View) {

        if (mIntent == null) {
            mIntent = Intent(this, MyLocationService::class.java)
        }
        startService(mIntent)
    }

    fun stopGpsLocation(view: View) {
        mIntent?.also {
            stopService(it)
            mIntent = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mIntent?.also {
            stopService(it)
        }
        mIntent = null
    }
}
