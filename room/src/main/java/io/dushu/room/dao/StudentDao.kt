package io.dushu.room.dao

import androidx.room.*
import io.dushu.room.entity.Student

/**
 * author: zhangshuai 6/27/21 8:55 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
@Dao
interface StudentDao {

    @Insert
    suspend fun insert(vararg student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Update
    suspend fun update(student: Student)

    @Query("SELECT * FROM student")
    suspend fun getAllStudent(): List<Student>

    @Query("SELECT * FROM student WHERE id =:id")
    suspend fun getStudentById(id: Int): List<Student>

}