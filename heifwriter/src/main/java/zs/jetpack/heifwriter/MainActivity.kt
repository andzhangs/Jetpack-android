package zs.jetpack.heifwriter

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        val heifWriter =
            HeifWriter.Builder(imgPath, 200, 200, HeifWriter.INPUT_MODE_BITMAP)
                .setQuality(80)
                .setGridEnabled(true)
                .build()

        heifWriter.addBitmap(BitmapFactory.decodeFile(""))
        heifWriter.start()
    }
}