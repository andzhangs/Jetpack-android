package zs.jetpack.remotecallback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * RemoteCallback 是一个用于在 Android 应用程序中处理远程回调的类。
 * 它提供了一种机制，使您可以在应用程序的不同组件之间进行跨进程通信，并处理远程方法调用的结果。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}