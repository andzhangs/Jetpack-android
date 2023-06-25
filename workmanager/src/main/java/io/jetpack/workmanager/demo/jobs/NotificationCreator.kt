package io.jetpack.workmanager.demo.jobs

import android.app.AppOpsManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.jetpack.workmanager.R
import io.jetpack.workmanager.demo.works.WorkActivity
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * @author zhangshuai@attrsense.com
 * @date 2023/6/25 18:28
 * @description
 */
object NotificationCreator {

    @JvmStatic
    fun show(context: Context) {
        val state = NotificationManagerCompat.from(context).areNotificationsEnabled()
        val isEnable = isNotificationEnabled(context)
        Log.i("print_logs", "state: $state, isEnable: $isEnable")
//        if (!isEnable) {
            PendingIntent.getActivity(
                context, 0, Intent(context, WorkActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
                .apply {
                    Log.i("print_logs", "NotificationJob::onRunJob::success ")
                    createNotification(context, this)
                }
//        } else {
//            openSetting(context)
//            Toast.makeText(context, "未开启权限！！！", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun createNotification(context: Context, pendingIntent: PendingIntent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                context.packageName,
                "下载文件通知",  //通知类别，在手机设置的应用程序中对应的APP的"通知"中可见
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "My Notification Channel"
                enableLights(true)
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500)
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                setShowBadge(true) //设置是否显示角标
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(context, context.packageName)
            .setSmallIcon(R.drawable.ic_launcher_foreground) //通知栏的左上角小图标
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_launcher_foreground
                )
            ) //通知栏右边内容图标
            .setContentTitle("下载完成")
            .setContentText("点击安装")
            .setPriority(NotificationCompat.PRIORITY_MAX) //当发出此类型的通知时，通知会以悬挂的方法显示在屏幕上
            .setAutoCancel(true) //点击通知后清除
            .setOngoing(true) // 除非app死掉或者在代码中取消，否则都不会消失。
            .setDefaults(Notification.DEFAULT_ALL)// DEFAULT_VIBRATE  添加默认震动提醒  需要VIBRATE permission; DEFAULT_SOUND  添加默认声音提醒; DEFAULT_LIGHTS  添加默认三色灯提醒;  DEFAULT_ALL  添加默认以上3种全部提醒
            .setVibrate(
                longArrayOf(
                    100,
                    200,
                    300,
                    400,
                    500
                )
            ) //设置震动，需要配置权限(android.permission.VIBRATE)
            .setLights(Color.RED, 300, 0) //设置呼吸灯
            .setTicker("通知到达的时候会在状态栏上方直接显示通知")
            .setAllowSystemGeneratedContextualActions(true)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
//                .setStyle(NotificationCompat.BigTextStyle().bigText("在APP中的开发中会用到提醒，通知类似的功能，之前一篇文章我已经介绍到了[**《Android使用NotificationListenerService监听手机收到的通知》**](https://zhuanlan.zhihu.com/p/62380569)，监听了通知，没有通知的实现"))
//            .setStyle(
//                NotificationCompat.InboxStyle()
//                    .addLine("1、在APP中的开发中会用到提醒，通知类似的功能")
//                    .addLine("2、使用NotificationListenerService监听")
//                    .addLine("3、监听了通知，没有通知的实现")
//            )
            //方式一
//                .addAction(R.drawable.ic_launcher_foreground,"点击安装",createPendingIntent()) //设置意图
            //方式二
//                .setFullScreenIntent(createPendingIntent(),false)  //不建议使用这个方法，华为小米适配有问题
            //方式三
            .setContentIntent(pendingIntent)
            .build()
        NotificationManagerCompat.from(context).apply {
            notify(1, notification)
        }
    }

    /**
     * 通知栏权限是否开启
     * 4.4版本一下不处理，4.4到8.0,8.0以上
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun isNotificationEnabled(context: Context): Boolean {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //8.0手机以上
                if ((context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).importance == NotificationManager.IMPORTANCE_NONE) {
                    return false
                }
            }
            val CHECK_OP_NO_THROW = "checkOpNoThrow"
            val OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION"
            val mAppOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val appInfo: ApplicationInfo = context.applicationInfo
            val pkg: String = context.applicationContext.packageName
            val uid = appInfo.uid
            val appOpsClass: Class<*>? = Class.forName(AppOpsManager::class.java.name)
            val checkOpNoThrowMethod: Method = appOpsClass!!.getMethod(
                CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                String::class.java
            )
            val opPostNotificationValue: Field = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION)
            val value = opPostNotificationValue.get(Int::class.java) as Int
            return checkOpNoThrowMethod.invoke(
                mAppOps,
                value,
                uid,
                pkg
            ) as Int == AppOpsManager.MODE_ALLOWED
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("print_logs", "isNotificationEnabled: $e")
        }
        return false
    }

    private fun openSetting(context: Context) {
        val localIntent = Intent()
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //直接跳转到应用通知设置的代码：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            localIntent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            localIntent.putExtra("app_package", context.packageName)
            localIntent.putExtra("app_uid", context.applicationInfo.uid)
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            localIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            localIntent.addCategory(Intent.CATEGORY_DEFAULT)
            localIntent.data = Uri.parse("package:" + context.packageName)
        } else {
            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
//            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                localIntent.data = Uri.fromParts("package", context.packageName, null)
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.action = Intent.ACTION_VIEW
                localIntent.setClassName(
                    "com.android.settings",
                    "com.android.setting.InstalledAppDetails"
                )
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
            }
        }
        context.startActivity(localIntent)
    }


}