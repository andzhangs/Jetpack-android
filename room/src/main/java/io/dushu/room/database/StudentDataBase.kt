package io.dushu.room.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import io.dushu.room.dao.StudentDao
import io.dushu.room.entity.Student

/**
 * author: zhangshuai 6/27/21 9:03 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDataBase : RoomDatabase() {


    companion object {
        private var mInstance: StudentDataBase? = null

        @Synchronized
        fun getInstance(context: Context): StudentDataBase {
            if (mInstance == null) {
                mInstance =
                    Room.databaseBuilder(context.applicationContext, StudentDataBase::class.java, "student_db.db")
                        .addMigrations()
                        .build()
            }
            return mInstance as StudentDataBase
        }
    }

    abstract fun getStudentDao(): StudentDao


//    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
//
//    }
//
//    override fun createInvalidationTracker(): InvalidationTracker {
//
//    }
//
//    override fun clearAllTables() {
//
//    }
}