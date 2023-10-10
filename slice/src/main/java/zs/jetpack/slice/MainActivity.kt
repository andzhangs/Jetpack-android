package zs.jetpack.slice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 在应用外显示模板化界面元素。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}