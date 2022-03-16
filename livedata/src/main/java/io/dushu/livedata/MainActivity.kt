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

        lifecycle.addObserver(viewModel)

        viewModel.getCurrentSecond().observe(this) { t ->
            textview.text = t.toString()
        }
    }

    fun clickEvent(view: View) {
        viewModel.startTimer()
    }

    fun clickLiveDataTransformations(view:View){
        viewModel.setUserData(User("张","帅"))
    }

    fun clickMediatorLiveData(view:View){
        viewModel.setMediatorLiveData("Hello world, ")
    }

    private fun loadListener(){
        // FIXME: 2021/12/6 激活MediatorLiveData 方式一
        viewModel.getMediatorLiveData().observe(this){
            Log.i("print_logs", "MediatorLiveData激活: $it")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.unsetMediatorLiveData()
    }
}
