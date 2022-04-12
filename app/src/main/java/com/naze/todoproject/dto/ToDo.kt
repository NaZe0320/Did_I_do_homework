package com.naze.todoproject.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo_table")
data class ToDo(
    @ColumnInfo(name = "subject") var subject: String,
    @ColumnInfo(name = "todo") var todo: String,
    @ColumnInfo(name = "start_date") var sDate: String,
    @ColumnInfo(name = "end_date") var eDate: String,
    @ColumnInfo(name = "checked") var checked : Boolean
) {
    @PrimaryKey(autoGenerate = true) var id: Int=0
}