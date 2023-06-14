package io.dushu.room

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.dushu.room.databinding.ActivityMainBinding
import io.dushu.room.entity.Student
import io.dushu.room.viewmodel.StudentViewModel
import kotlinx.coroutines.launch

/**
 * @author zhangshuai
 * @date 2022/3/14 22:32
 * @description
 */
class MvvmActivity: AppCompatActivity() {

    private lateinit var mDataBinding: ActivityMainBinding
    private val list = arrayListOf<Student>()
    private lateinit var mAdapter: MyAdapter
    private lateinit var mStudentViewModel: StudentViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this@MvvmActivity, R.layout.activity_main)

        mAdapter = MyAdapter(list)
        mDataBinding.recycleView.apply {
            layoutManager = LinearLayoutManager(this@MvvmActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
        mStudentViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(this.application)).get(StudentViewModel::class.java)

        mStudentViewModel.getAllStudent2().observe(this){
            Log.i("print_logs", "MvvmActivity::observe: ${it.size}")
            list.clear()
            if (it.isNotEmpty()) {
                list.addAll(it)
                mDataBinding.recycleView.smoothScrollToPosition(list.size - 1)
            }
            mAdapter.notifyDataSetChanged()
        }

        lifecycleScope.launch{
            mStudentViewModel.getAllStudent3().collect{
                Log.i("print_logs", "getAllStudent3: $it")
            }
        }
    }


    fun InsertClick(view: View) {
        mStudentViewModel.insert(Student(0,"Hello",System.currentTimeMillis().toInt()))
    }


    fun DeleteClick(view: View) {
//        mStudentViewModel.delete(Student(68))
        mStudentViewModel.clearAll()
    }

}