package com.android.jetpack.workmanager.works;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


/**
 * @author zhangshuai
 */
public class UploadWorker extends Worker {

    public static final String TAG = "Upload_Worker";

    public static final String INPUT_URI = "";
    public static final String OUTPUT_URI = "";

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "UploadWorker  doWork: " + Thread.currentThread());
        // Get the input
        String imageUriInput = getInputData().getString(INPUT_URI);
        Log.i(TAG, "接收的图片地址：" + imageUriInput);

        //Create the output of the work
        Data dataOutput = new Data.Builder().putString(OUTPUT_URI, "http://lorempixel.com/g/300/200/").build();

        Log.i(TAG, "发送的图片："+dataOutput.getString(OUTPUT_URI));
        return Result.success(dataOutput);
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(TAG, "UploadWorker  onStopped: ");
    }

}
