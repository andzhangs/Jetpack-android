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
    private lateinit var list: ArrayList<Student>
    private lateinit var mAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        list = arrayListOf<Student>()
        mAdapter = MyAdapter(list)
        mDataBinding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
        mDataBinding.recycleView.layoutManager?.scrollToPosition(list.size)

        val database: StudentDataBase = StudentDataBase.getInstance(this)
        studentDao = database.getStudentDao()

        notifyData()
    }

    fun InsertClick(view: View) {
        GlobalScope.launch {
            studentDao.insert(Student(name = "Jack", age = 20))
        }
        notifyData()
    }

    fun DeleteClick(view: View) {
        GlobalScope.launch {
            studentDao.delete(Student(name = "Jack", age = 20))
        }
        notifyData()
    }

    fun UpdateClick(view: View) {
        GlobalScope.launch {
            studentDao.update(Student(name = "Tom", age = 40))
        }
        notifyData()
    }

    fun QueryClick(view: View) {
        GlobalScope.launch {
            studentDao.getStudentById(4)
        }
        notifyData()
    }

    private fun notifyData() {
        GlobalScope.launch {
            val data = studentDao.getAllStudent()
            if (data.isNotEmpty()) {
                list.clear()
                list.addAll(studentDao.getAllStudent())
                Log.i("print_log", "notifyData：${list.size}")
            }
        }
        mAdapter.notifyDataSetChanged()
    }

}
