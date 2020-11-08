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

    fun editUserTotal(context: Context, price: Int) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putInt("userTotal", price + getUserTotal(context))
        editor.apply()
    }

    fun getUserTotal(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("userTotal", 0)

    }

    fun getUserName(context: Context): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getString("userNick", ".")
    }

    fun setInfoCategoryCurrentTime(context: Context, time: Long) {

        if (!getInfoCategoryisLogin(context)) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
            val editor = sharedPreferences.edit()
            editor.putLong("InfoTime", time)
            editor.apply()
            setInfoCategoryisLogin(context)
        }

    }

    fun getInfoCategoryCurrentTime(context: Context): Long {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getLong("InfoTime", 0)
    }

    fun setInfoCategoryisLogin(context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("InfoIsLog", true)
        editor.apply()
    }

    fun getInfoCategoryisLogin(context: Context): Boolean {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getBoolean("InfoIsLog", false)
    }


}