package io.dushu.room

import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import base.BaseActivity
import io.dushu.room.databinding.ActivitySplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private lateinit var mSplashScreen: SplashScreen
    private var mKeepOnAtomicBool = AtomicBoolean(true)


    override fun onCreateBefore(savedInstanceState: Bundle?) {
        mSplashScreen = installSplashScreen()
    }

    override fun setLayoutResId() = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        // 每次UI绘制前，会判断 Splash 是否有必要继续展示在屏幕上；直到不再满足条件时，隐藏Splash。
        mSplashScreen.setKeepOnScreenCondition {
            Log.i("print_logs", "SplashActivity::onCreate: ----")
            mKeepOnAtomicBool.get()
        }

        mSplashScreen.setOnExitAnimationListener {
            it.remove()
            Log.i("print_logs", "SplashActivity::onCreate: remove")
        }


        lifecycleScope.launch(Dispatchers.IO) {
            Log.i("print_logs", "SplashActivity::onCreate: before")
            delay(1000)
            Log.i("print_logs", "SplashActivity::onCreate: after")
            mKeepOnAtomicBool.compareAndSet(true, false)
        }
    }
}