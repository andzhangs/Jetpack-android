package com.android.jetpack.workmanager;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.android.jetpack.workmanager.jobs.DemoJobCreator;
import com.evernote.android.job.JobManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.work.WorkManager;

/**
 * Android-Job
 * @author zhangshuai
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.acBtn_startJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoJobCreator.scheduleJob(MainActivity.this);
            }
        });
        findViewById(R.id.acBtn_cancelJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoJobCreator.cancelByJobId(2);
            }
        });
    }
}
