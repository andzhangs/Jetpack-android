package zs.jetpack.versionedparcelable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 提供稳定且紧凑的二进制序列化格式，该格式可跨进程传递或安全保留。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}