package com.apk.downloadinstall.navigation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlin.properties.Delegates

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

    var ids: Int =0

    private fun sendNotification(){
        activity?.let {
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    it.packageName,
                    "MyChannel",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "My Notification Channel"
                }
                val notificationManager = it.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(notificationChannel)
            }

            val notification = NotificationCompat.Builder(it, it.packageName)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Deeplink")
                .setContentText("点击安装")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .build()

            NotificationManagerCompat.from(it).apply {
                notify(ids++,notification)
            }
        }


    }

    /**
     * PendingIntent方式
     */
    private fun getPendingIntent():PendingIntent?{
        return activity?.let {
            Navigation.findNavController(it,R.id.acBtnDeeplink_PendingIntent)
                .createDeepLink()
                .setGraph(R.navigation.nav_gragh)
                .setDestination(R.id.detailFragment)
                .setArguments(Bundle().apply {
                    putString("param1","参数一")
                    putInt("param2",2)
                })
                .createPendingIntent()
        }
    }

    /**
     * 通过Url
     */
    private fun getUrl():String?=""

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}