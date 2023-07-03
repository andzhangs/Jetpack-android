package io.jetpack.workmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.jetpack.workmanager.base.BaseActivity
import io.jetpack.workmanager.databinding.ActivityMainBinding
import io.jetpack.workmanager.demo.jobs.MainJobActivity
import io.jetpack.workmanager.demo.works.WorkActivity
import io.jetpack.workmanager.utils.viewBinding
import io.jetpack.workmanager.video.MyWorkActivity

class MainActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityMainBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.acBtnGoJob.setOnClickListener {
            startActivity(Intent(this, MainJobActivity::class.java))
        }

        mBinding.acBtnGoWork.setOnClickListener {
            startActivity(Intent(this, WorkActivity::class.java))
        }

        mBinding.acBtnGoMyWork.setOnClickListener {
            startActivity(Intent(this, MyWorkActivity::class.java))
        }

        val aPiService = Api.APiService {
            return@APiService "回调信息：name= $it, age= 18, sex= 男"
        }

        getService(aPiService)
    }

    private fun getService(service: Api.APiService) {
        Log.i("print_logs", "MainActivity::onCreate: ${service.getUser("你好")}")
    }
}
