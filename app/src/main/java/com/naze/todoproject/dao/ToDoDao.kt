package com.naze.todoproject.dao

import androidx.room.*
import com.naze.todoproject.dto.ToDo

@Dao
interface ToDoDao {
    @Insert
    suspend fun insertTodo(toDo: ToDo)

    @Delete
    suspend fun deleteTodo(toDo: ToDo)

    @Update
    suspend fun updateTodo(toDo: ToDo)

    @Query("SELECT * FROM todo_table")
    fun getAllTodo(): List<ToDo>
}