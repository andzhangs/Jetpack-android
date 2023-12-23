package io.dushu.room

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val province = arrayOf("江西", "湖南")
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner

    private var provinceindex = 0
    private val city = arrayOf(arrayOf("南昌", "赣州"), arrayOf("长沙", "湘潭"))
    private val counstryside = arrayOf(
        arrayOf(arrayOf("青山湖区", "南昌县"), arrayOf("章贡区", "赣县")),
        arrayOf(arrayOf("长沙县", "沙县"), arrayOf("湘潭县", "象限"))
    )
    private lateinit var adapter1: ArrayAdapter<String?>
    private lateinit var adapter2: ArrayAdapter<String?>
    private lateinit var adapter3: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner1 = findViewById<View>(R.id.spn) as Spinner
        adapter1 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, province)
        spinner1.adapter = adapter1

        spinner2 = findViewById<View>(R.id.city) as Spinner
        adapter2 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, city[0])
        spinner2.adapter = adapter2

        spinner3 = findViewById<View>(R.id.counstryside) as Spinner
        adapter3 = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line,
            counstryside[0][0]
        )
        spinner3.adapter = adapter3
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position: Int, id: Long) {
//                Toast.makeText(this@MainActivity, "点击了：${province[position]}", Toast.LENGTH_SHORT).show()

                provinceindex = position
                adapter2 = ArrayAdapter(
                    this@MainActivity, android.R.layout.simple_dropdown_item_1line,
                    city[position]
                )
                spinner2.adapter = adapter2
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Toast.makeText(this@MainActivity, "什么也没选中", Toast.LENGTH_SHORT).show()
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {

                adapter3 = ArrayAdapter(
                    this@MainActivity, android.R.layout.simple_dropdown_item_1line,
                    counstryside[provinceindex][position]
                )
                //adapter3.notifyDataSetChanged();
                spinner3.adapter = adapter3
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //当时据为空的时候触发的
            }
        }

        val acBtnViewAnimator = findViewById<AppCompatButton>(R.id.acBtn_ViewAnimator)
        acBtnViewAnimator.setOnClickListener {
//            ViewAnimator.animate(it).apply {
//                bounce()
//                textColor(R.color.colorAccent)
//                backgroundColor(R.color.colorWhite)
//                duration(500)
//                start()
//            }
            loadPermission()
        }

        Looper.myQueue().addIdleHandler {
            //  在这里去处理你想延时加载的东西
            Toast.makeText(this, "延迟弹出了，${Thread.currentThread().name} ", Toast.LENGTH_SHORT).show()
            // 最后返回false，后续不用再监听了。
            false
        }
    }

    //android -12
    private fun loadPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                        Log.i("print_logs", "MainActivity::loadPermission: 1")
                }
                shouldShowRequestPermissionRationale (Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                        Log.i("print_logs", "MainActivity::loadPermission: 2")
                }

                else -> {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("print_logs", "MainActivity::onRequestPermissionsResult: 1")
            }else{
                    Log.i("print_logs", "MainActivity::onRequestPermissionsResult: 2")
            }
        }else{
                Log.i("print_logs", "MainActivity::onRequestPermissionsResult: 3")
        }
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

}