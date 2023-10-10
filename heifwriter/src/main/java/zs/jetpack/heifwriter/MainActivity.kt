package zs.jetpack.heifwriter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.heifwriter.HeifWriter

/**
 * 使用 Android 设备上可用的编解码器，以 HEIF 格式对图像或图像集进行编码。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imgPath = ""
        assets.list("img")?.forEach {
            imgPath = it
        }

        if (BuildConfig.DEBUG) {
            Log.i("print_logs", "imgPath: $imgPath")
        }

        findViewById<AppCompatButton>(R.id.acBtn).setOnClickListener {

            val path="/storage/emulated/0/media/9600569.jpg"

            val heifWriter =
                HeifWriter.Builder(path, 200, 200, HeifWriter.INPUT_MODE_BUFFER)
                    .setQuality(80)
                    .build()


//            heifWriter.addBitmap(BitmapFactory.decodeFile(imgPath))
            heifWriter.start()
        }
    }
}