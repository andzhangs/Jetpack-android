package io.dushu.room.viewmodel

import android.app.Application
import androidx.lifecycle.*
import io.dushu.room.dao.StudentDao
import io.dushu.room.entity.Student
import io.dushu.room.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author zhangshuai
 * @date 2022/3/14 21:55
 * @description
 */
class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val mStudentRepository: StudentRepository = StudentRepository(application)

    fun insert(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mStudentRepository.insertStudent(student)
            }
        }
    }

    fun delete(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mStudentRepository.deleteStudent(student)
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mStudentRepository.clearAll()
            }
        }
    }

    fun update(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mStudentRepository.updateStudent(student)
            }
        }
    }

    fun getStudentById(id: Int): List<Student> {
        var data = arrayListOf<Student>()
        viewModelScope.async {
            withContext(Dispatchers.IO) {
                data = mStudentRepository.selectStudentById(id) as ArrayList<Student>
            }
        }
        return data
    }


    fun getAllStudent2(): LiveData<List<Student>> {
        return mStudentRepository.getAll()
    }

}