package io.dushu.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.dushu.room.dao.StudentDao
import io.dushu.room.database.StudentDataBase
import io.dushu.room.databinding.ActivityMainBinding
import io.dushu.room.entity.Student
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mDataBinding: ActivityMainBinding
    private lateinit var studentDao: StudentDao
    private val list = arrayListOf<Student>()
    private lateinit var mAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val database: StudentDataBase = StudentDataBase.getInstance(this)
        studentDao = database.getStudentDao()

        mAdapter = MyAdapter(list)
        mDataBinding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        notifyData()
    }

    fun InsertClick(view: View) {
        GlobalScope.launch {
            studentDao.insert(Student(0, "Jack", 20))
        }
        notifyData()
    }

    fun DeleteClick(view: View) {
        GlobalScope.launch {
            studentDao.delete(Student(2, "Jack", 20))
        }
        notifyData()
    }

    fun UpdateClick(view: View) {
        GlobalScope.launch {
            studentDao.update(Student(5, "Tom", 60))
        }
        notifyData()
    }

    fun QueryClick(view: View) {
        GlobalScope.launch {
            list.clear()
            list.addAll(studentDao.getStudentById(4))
        }
        notifyDataChanged()
    }

    private fun notifyDataChanged() {
        list?.let {
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun notifyData() {
        GlobalScope.launch {
            val data = studentDao.getAllStudent()
            Log.i("print_log", "notifyData-1：${data.size}")
            if (data.isNotEmpty()) {
                list.clear()
                list.addAll(data)
                Log.w("print_log", "notifyData-2：${list.size}")
            }
        }
        mAdapter.notifyDataSetChanged()
    }

}
