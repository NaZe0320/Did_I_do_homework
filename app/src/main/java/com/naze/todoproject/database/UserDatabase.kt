@file:OptIn(InternalCoroutinesApi::class)

package com.naze.todoproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.naze.todoproject.converter.Converter
import com.naze.todoproject.dao.SubDao
import com.naze.todoproject.dao.ToDoDao
import com.naze.todoproject.dto.Sub
import com.naze.todoproject.dto.ToDo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(Sub::class,ToDo::class),version = 2)

abstract class UserDatabase: RoomDatabase() {
    abstract val subDao: SubDao
    abstract val todoDao: ToDoDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context):UserDatabase? {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}