package com.apk.downloadinstall.navigation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.acBtnHome)?.setOnClickListener {

            //传值方式一
            val toBundle1 = HomeFragmentArgs("你好", 11).toBundle()
//            val toBundle1 = DetailFragmentArgs("万岁").toBundle()
//            val findNavController = Navigation.findNavController(it)
//            findNavController.navigate(R.id.action_homeFragment_to_detailFragment,toBundle1)

            //传值方式二
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment("你好")
            it.findNavController().navigate(action)
        }


        //deeplink
        view.findViewById<AppCompatButton>(R.id.acBtnDeeplink_PendingIntent)?.setOnClickListener {
            sendNotification()
        }
    }

    var ids: Int = 0

    private fun sendNotification() {
        activity?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    it.packageName,
                    "MyChannel",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "My Notification Channel"
                    enableLights(true)
                    enableVibration(true)
                    vibrationPattern=longArrayOf(100,200,300,400,500)
                    lockscreenVisibility=Notification.VISIBILITY_PRIVATE
                    setShowBadge(true) //设置是否显示角标
                }
                val notificationManager = it.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(notificationChannel)
            }

            val notification = NotificationCompat.Builder(it, it.packageName)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("下载完成")
                .setContentText("点击安装")
                .setPriority(NotificationCompat.PRIORITY_MAX) //当发出此类型的通知时，通知会以悬挂的方法显示在屏幕上
                .setContentIntent(getPendingIntent())
//                .setFullScreenIntent(createPendingIntent(),true)
                .setAutoCancel(true) //点击通知后清除
                .setOngoing(true) // 除非app死掉或者在代码中取消，否则都不会消失。
                .setDefaults(Notification.DEFAULT_ALL)// DEFAULT_VIBRATE  添加默认震动提醒  需要VIBRATE permission; DEFAULT_SOUND  添加默认声音提醒; DEFAULT_LIGHTS  添加默认三色灯提醒;  DEFAULT_ALL  添加默认以上3种全部提醒
                .setVibrate(longArrayOf(100,200,300,400,500)) //设置震动，需要配置权限(android.permission.VIBRATE)
                .setLights(Color.RED,300,0) //设置呼吸灯
                .setTicker("通知到达的时候会在状态栏上方直接显示通知")
                .setAllowSystemGeneratedContextualActions(true)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
//                .setStyle(NotificationCompat.BigTextStyle().bigText("在APP中的开发中会用到提醒，通知类似的功能，之前一篇文章我已经介绍到了[**《Android使用NotificationListenerService监听手机收到的通知》**](https://zhuanlan.zhihu.com/p/62380569)，监听了通知，没有通知的实现"))
//                .setStyle(NotificationCompat.InboxStyle()
//                    .addLine("在APP中的开发中会用到提醒，通知类似的功能，之前一篇文章我已经介绍到了[**《")
//                    .addLine("Android使用NotificationListenerService监听手机收到的通知》**]")
//                    .addLine("(https://zhuanlan.zhihu.com/p/62380569)，监听了通知，没有通知的实现"))
                .build()
            NotificationManagerCompat.from(it).apply {
                notify(ids++, notification)
            }
        }
    }

    /**
     * PendingIntent方式
     */
    private fun getPendingIntent(): PendingIntent? {
        return activity?.let {
            Navigation.findNavController(it, R.id.acBtnDeeplink_PendingIntent)
                .createDeepLink()
                .setGraph(R.navigation.nav_gragh)
                .setDestination(R.id.detailFragment)
                .setArguments(Bundle().apply {
                    putString("param","deeplink")
                    putString("param1", "参数一")
                    putInt("param2", 2)
                })
                .createPendingIntent()
        }
    }

    private fun createPendingIntent():PendingIntent{
        val clickIntent=Intent(activity,OnClickNotificationCompatReceiver::class.java).apply {
            putExtra("param3","I'm from createPendingIntent")
        }
        return PendingIntent.getBroadcast(activity?.applicationContext,ids,clickIntent,0)
    }
    class OnClickNotificationCompatReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, intent?.getStringExtra("param3"), Toast.LENGTH_SHORT).show()
        }
    }



    /**
     * 通过Url
     */
    private fun getUrl(): String = ""
}