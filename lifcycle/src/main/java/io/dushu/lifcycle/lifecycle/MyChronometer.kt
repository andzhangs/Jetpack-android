package io.dushu.lifcycle.lifecycle

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * author: zhangshuai 6/26/21 5:59 PM
 * email: zhangshuai@dushu365.com
 * mark: 计时器
 *
 * SystemClock.elapsedRealtime() 开机之后的时间
 */
open class MyChronometer(context: Context?, attrs: AttributeSet?) : Chronometer(context, attrs),
    LifecycleObserver {

    private var elapsedTime: Long = 0L

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun startMeter() {
        base = SystemClock.elapsedRealtime() - elapsedTime
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun stopMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - base
        stop()
    }

}