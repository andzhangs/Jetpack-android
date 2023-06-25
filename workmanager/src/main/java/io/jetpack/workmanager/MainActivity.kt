package io.jetpack.workmanager

import android.content.Intent
import android.os.Bundle
import io.jetpack.workmanager.base.BaseActivity
import io.jetpack.workmanager.databinding.ActivityMainBinding
import io.jetpack.workmanager.demo.jobs.NotificationCreator
import io.jetpack.workmanager.demo.works.WorkActivity
import io.jetpack.workmanager.utils.viewBinding
import io.jetpack.workmanager.video.MyWorkActivity

class MainActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.acBtnGoJob.setOnClickListener {
//            startActivity(Intent(this, MainJobActivity::class.java))
            NotificationCreator.show(this.applicationContext)
        }

        mBinding.acBtnGoWork.setOnClickListener {
            startActivity(Intent(this, WorkActivity::class.java))
        }

        mBinding.acBtnGoMyWork.setOnClickListener {
            startActivity(Intent(this, MyWorkActivity::class.java))
        }
    }
}
