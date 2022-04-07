package com.naze.todoproject.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Sub(
    @ColumnInfo(name = "subject") var subject: String,
    @ColumnInfo(name = "color") var color: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int=0
}


