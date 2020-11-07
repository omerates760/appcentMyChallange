package com.monofire.appcentchallange.db

import android.content.Context
import android.content.SharedPreferences
import com.monofire.appcentchallange.model.User

object ShareDb {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "user-data"
    fun editUserData(context: Context, user: User) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putInt("userTotal", user.total)
        editor.putString("userNick", user.nickName)
        editor.putString("userId", user.userId)
        editor.apply()

    }

    fun getUserTotal(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("userTotal", 0)

    }

    fun getUserName(context: Context) : String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getString("userNick",".")
    }

}