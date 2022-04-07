@file:OptIn(InternalCoroutinesApi::class)

package com.naze.todoproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naze.todoproject.dao.SubDao
import com.naze.todoproject.dto.Sub
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(Sub::class),version = 1)

abstract class UserDatabase: RoomDatabase() {
    abstract val subDao: SubDao

    companion object {
        @Volatile //Multi-Thread safe 하도록 Volatile 선언
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