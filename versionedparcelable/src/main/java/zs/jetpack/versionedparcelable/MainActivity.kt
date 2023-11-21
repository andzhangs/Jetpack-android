package zs.jetpack.versionedparcelable

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.versionedparcelable.ParcelUtils

/**
 * 提供稳定且紧凑的二进制序列化格式，该格式可跨进程传递或安全保留。
 */
class MainActivity : AppCompatActivity() {

    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.acBtn_1).setOnClickListener {
            load1()
        }
        findViewById<AppCompatButton>(R.id.acBtn_2).setOnClickListener {
            load2()
        }
    }

    private fun load1(){
        val userBean = UserBean().apply {
            name = "I am MainActivity"
        }
        ParcelUtils.putVersionedParcelable(bundle, "hello", userBean)




        val userBeans=ArrayList<UserBean>().apply {
            for (i in 1..10){
                add(UserBean().apply { name="I'm from MainActivity $i" })
            }
        }
        ParcelUtils.putVersionedParcelableList(bundle,"lists",userBeans)
    }

    private fun load2(){
        val data = ParcelUtils.getVersionedParcelable<UserBean>(bundle,"hello")
        if (BuildConfig.DEBUG) {
            Log.i("print_logs", "MainActivity::load2: ${data?.name}")
        }



        val datas = ParcelUtils.getVersionedParcelableList<UserBean>(bundle,"lists")
        datas?.forEach {
            if (BuildConfig.DEBUG) {
                Log.i("print_logs", "MainActivity::load2: ${it?.name}")
            }
        }
    }
}