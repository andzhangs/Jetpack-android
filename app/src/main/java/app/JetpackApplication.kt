package app

import android.app.Application
import java.io.FileInputStream
import java.nio.channels.FileChannel

/**
 * @Author zhangshuai
 * @Date 2021/10/12
 * @Emial zhangshuai@dushu365.com
 * @Description
 */
class JetpackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val fileChannel: FileChannel = FileInputStream("").channel

    }

}