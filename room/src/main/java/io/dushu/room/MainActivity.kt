package io.dushu.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.dushu.room.dao.StudentDao
import io.dushu.room.database.StudentDataBase
import io.dushu.room.databinding.ActivityMainBinding
import io.dushu.room.entity.Student
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDataBinding: ActivityMainBinding
    private lateinit var studentDao: StudentDao
    private val list = arrayListOf<Student>()
    private lateinit var mAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        val database: StudentDataBase = StudentDataBase.getInstance(this@MainActivity)
        studentDao = database.getStudentDao()

        mAdapter = MyAdapter(list)
        mDataBinding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        notifyData()
    }

    fun InsertClick(view: View) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.insert(Student(0, "Jack", 20))
            }
        }
        notifyData()
    }

    fun DeleteClick(view: View) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.delete(Student(2, "Jack", 20))
            }
        }
        notifyData()
    }

    fun UpdateClick(view: View) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.update(Student(5, "Tom", 60))
            }
        }
        notifyData()
    }

    fun QueryClick(view: View) {
//        lifecycleScope.launch {
//            list.clear()
//            withContext(Dispatchers.IO) {
//                list.addAll(studentDao.getStudentById(4))
//            }
//        }

        studentDao.getStudentById(4).observe(this){
            Log.i("print_logs", "MainActivity::QueryClick: ${it.size}")
            list.clear()
            list.addAll(it)
        }

        notifyDataChanged()
    }

    private fun notifyDataChanged() {
        mAdapter.notifyDataSetChanged()
    }

    private fun notifyData() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val data = studentDao.getAllStudent()
                Log.i("print_logs", "notifyData-1：${data.size}")
                if (data.isNotEmpty()) {
                    list.clear()
                    list.addAll(data)
                    Log.w("print_logs", "notifyData-2：${list.size}")
                }
            }
        }
        mAdapter.notifyDataSetChanged()
    }
}
