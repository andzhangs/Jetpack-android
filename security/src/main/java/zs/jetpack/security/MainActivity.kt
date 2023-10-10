package zs.jetpack.security

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 安全地管理密钥并对文件和 sharedpreferences 进行加密。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}