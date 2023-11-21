package io.dushu.lifcycle.lifecycleservice

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import io.dushu.lifcycle.BuildConfig

class MyLocationService : LifecycleService() {

    private val observer = MyLocationObserver(this)

    init {
        Log.i("print_logs", "MyLocationService is init.")
        lifecycle.addObserver(observer)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        if (BuildConfig.DEBUG) {
            Log.i("print_logs", "MyLocationService::attachBaseContext: ")
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Log.i("print_logs", "MyLocationService::onCreate: ")
        }
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (BuildConfig.DEBUG) {
            Log.i("print_logs", "MyLocationService::onStartCommand: ")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (BuildConfig.DEBUG) {
            Log.i("print_logs", "MyLocationService::onDestroy: ")
        }
        lifecycle.removeObserver(observer)
    }
}
