package io.dushu.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                MyViewModel::class.java
            )

        viewModel.getCurrentSecond().observe(this,
            { t ->
                Log.i("print_log", "接收：$t")
                textview.text = t.toString()
            })
    }

    fun clickEvent(view: View) {
        startTimer()
    }

    private val mTimer:Timer by lazy { Timer() }
    private fun startTimer() {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                viewModel.add()
            }
        }, 1000, 1000)
    }

    override fun onPause() {
        super.onPause()
        Log.i("print_log", "取消：")
        mTimer.cancel()
    }
}
