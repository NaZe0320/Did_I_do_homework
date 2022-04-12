package com.naze.todoproject

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    const val FIRST_LOGIN = "FIRST_LOGIN"

    private fun getPreferences(context: Context):SharedPreferences {
        return context.getSharedPreferences(FIRST_LOGIN,Context.MODE_PRIVATE)
    }

    fun setBoolean(context: Context, key: String, value: Boolean) {
        val prefs = getPreferences(context)
        val editor = prefs.edit()
        editor.putBoolean(key,value)
        editor.apply()
    }

    fun getBoolean(context: Context, key: String) :Boolean {
        val prefs = getPreferences(context)
        return prefs.getBoolean(key, false)
    }
}