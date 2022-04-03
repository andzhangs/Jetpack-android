package io.jetpack.workmanager.demo.works

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.WorkerThread
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author zhangshuai
 * @date 2022/4/2 星期六
 * @email zhangshuai@dushu365.com
 * @description
 */
internal class DemoUploadWorker constructor(
    @NonNull context: Context,
    @NonNull workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {
        @JvmStatic
        val INPUT_URI = "input_uri"

        @JvmStatic
        val OUTPUT_URI = "output_uri"
    }

    @WorkerThread
    override fun doWork(): Result {
        Log.i("print_logs", "UploadWorker::doWork: ${Thread.currentThread()}")
        //Get input data
        val imageUrl = inputData.getString(INPUT_URI)
        Log.i("print_logs", "UploadWorker::doWork:：INPUT_URI= $imageUrl")

        //Create the output of the work
        val dataOutput = Data.Builder().apply {
            putString(OUTPUT_URI, "https://cn.bing.com/th?id=OHR.HawaMahalJaipur_ZH-CN3863273823_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp")
        }.build()
        return Result.success(dataOutput)
    }

}