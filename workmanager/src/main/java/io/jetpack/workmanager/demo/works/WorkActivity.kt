package io.jetpack.workmanager.demo.works

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import io.jetpack.workmanager.databinding.ActivityWorkBinding
import io.jetpack.workmanager.utils.viewBinding
import java.util.concurrent.TimeUnit

/**
 * 链式调用:
 * To create a chain of work, you can use WorkManager.beginWith(OneTimeWorkRequest) or WorkManager.beginWith(List<OneTimeWorkRequest>) ,
 * which return an instance of WorkContinuation.
 *
 * A WorkContinuation can then be used to add dependent OneTimeWorkRequests using WorkContinuation.then(OneTimeWorkRequest)
 * or WorkContinuation.then(List<OneTimeWorkRequest>) .
 *
 * Every invocation of the WorkContinuation.then(...), returns a new instance of WorkContinuation.
 * If you add a List of OneTimeWorkRequests, these requests can potentially run in parallel.
 *
 * Finally, you can use the WorkContinuation.enqueue() method to enqueue() your chain of WorkContinuations.
 *
 * WorkManager.getInstance()
 *     // Candidates to run in parallel
 *     .beginWith(Arrays.asList(filter1, filter2, filter3))
 *     // Dependent work (only runs after all previous work in chain)
 *     .then(compress)
 *     .then(upload)
 *     // Don't forget to enqueue()
 *     .enqueue();
 *
 */
class WorkActivity : AppCompatActivity() {

    private val mBinding by viewBinding(ActivityWorkBinding::inflate)
    private lateinit var mOneTimeWorkRequest: OneTimeWorkRequest
    private val ID_MARK = "first_work"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.acBtnStartWork.setOnClickListener {
            starWork()
        }

        mBinding.acBtnCancelWork.setOnClickListener {
            WorkManager.getInstance().cancelWorkById(mOneTimeWorkRequest.id)
            // or
            // WorkManager.getInstance().cancelAllWorkByTag(ID_MARK)
        }
    }

    private fun starWork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //约束
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .build()

            val dataBuilder = Data.Builder()
                .putString(
                    DemoUploadWorker.INPUT_URI,
                    "I am from WorkActivity"
                )
                .build()

            //一次性工作
            mOneTimeWorkRequest = OneTimeWorkRequest.Builder(DemoUploadWorker::class.java)
                .setConstraints(constraints)
                .setInputData(dataBuilder)
//                .setInitialDelay(3,TimeUnit.SECONDS) //延时
                .addTag(ID_MARK)
                .build()

            val operation = WorkManager.getInstance().enqueue(mOneTimeWorkRequest)
            //工作前
            operation.state.observe(this) {
                Log.i("print_logs", "WorkActivity::onChanged: $it")
            }

            // Observing your work
            // After you enqueue your work, WorkManager allows you to check on its status
            WorkManager.getInstance().getWorkInfoByIdLiveData(mOneTimeWorkRequest.id)
                .observe(this) {
                    if (it.state == WorkInfo.State.SUCCEEDED) {
                        val data = it.outputData.getString(DemoUploadWorker.OUTPUT_URI)
                        Log.i(
                            "print_logs",
                            "WorkActivity::starWork::mOneTimeWorkRequest work is finished, $data"
                        )
                    }
                }

            //周期性工作
//            val periodicWorkRequest =
//                PeriodicWorkRequest.Builder(DemoUploadWorker::class.java, 1, TimeUnit.SECONDS)
//                    .setConstraints(constraints)
//                    .setInputData(dataBuilder)
//                    .addTag(ID_MARK)
//                    .build()
//
//            val operation2=WorkManager.getInstance().enqueue(periodicWorkRequest)
//
//            operation2.state.observe(this){
//                Log.i("print_logs", "WorkActivity::starWork::operation2 $it")
//            }
//
//            WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.id).observe(this){
//                if (it.state == WorkInfo.State.SUCCEEDED) {
//                    Log.i("print_logs", "WorkActivity::starWork::periodicWorkRequest work is finished")
//                }
//            }
        }
    }


}