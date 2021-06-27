package io.dushu.lifcycle.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * author: zhangshuai 6/26/21 7:35 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
internal class MyLifeCycleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyApplicationObserver())
    }

}