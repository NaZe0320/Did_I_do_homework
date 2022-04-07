package com.naze.todoproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.naze.todoproject.dto.Sub

@Dao
interface SubDao {
    @Insert
    suspend fun insertSub(sub: Sub)

    @Delete
    suspend fun deleteSub(sub: Sub)

    @Update
    suspend fun updateSub(sub: Sub)

    @Query("DELETE FROM subject_table")
    suspend fun deleteAllSub()

    @Query("SELECT * FROM subject_table")
    fun getAllSub(): List<Sub>
}