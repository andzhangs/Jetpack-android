package io.dushu.lifcycle.app

import android.util.Log
import androidx.lifecycle.*

/**
 * author: zhangshuai 6/26/21 7:39 PM
 * email: zhangshuai@dushu365.com
 * mark:
 *  1、针对整个应用程序，与Activity的数量无关
 *  2、ON_CREATE：只会被调用一次，
 *  3、ON_DESTROY：永远不会调用
 */
internal class MyApplicationObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        Log.i("print_log", "onCreate：")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        Log.i("print_log", "onStart：")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        Log.i("print_log", "onResume：")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun onPause() {
        Log.i("print_log", "onPause：")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        Log.i("print_log", "onStop：")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        Log.i("print_log", "onDestroy：")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private fun onAny() {
//        Log.i("print_log", "onAny：")
    }

}