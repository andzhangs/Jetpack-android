package io.dushu.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * author: zhangshuai 6/27/21 7:14 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int=0,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name: String?="",

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age: Int=0
) {

}