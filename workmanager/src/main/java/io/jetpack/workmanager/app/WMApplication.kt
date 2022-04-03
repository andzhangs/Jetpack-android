package io.jetpack.workmanager.app

import android.app.Application
import com.evernote.android.job.JobManager
import io.jetpack.workmanager.demo.jobs.DemoJobCreator

/**
 * @author zhangshuai
 * @date 2022/4/2 星期六
 * @email zhangshuai@dushu365.com
 * @description
 */
open class WMApplication : Application() {

    private val demoJobCreator=DemoJobCreator()

    override fun onCreate() {
        super.onCreate()
        JobManager.create(this).addJobCreator(demoJobCreator)
    }

    override fun onTerminate() {
        super.onTerminate()
        JobManager.create(this).removeJobCreator(demoJobCreator)
    }
}