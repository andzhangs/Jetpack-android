package zs.jetpack.loader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 加载配置更改后继续存在的界面数据。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}