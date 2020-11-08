package com.monofire.appcentchallange.db

import android.content.Context
import android.content.SharedPreferences
import com.monofire.appcentchallange.model.Count
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
        editor.putString("userMail", user.eMail)
        editor.putString("userPassword", user.passWord)
        editor.apply()

    }

    fun getUserMail(context: Context): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getString("userMail", "BULUNAMADI")
    }

    fun getUserPassword(context: Context): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getString("userPassword", "BULUNAMADI")
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

    fun setCount(context: Context, option: Int, price: Int) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor = sharedPreferences.edit()
        when (option) {
            0 -> {
                editor.putInt("campaignBuyCount", getCampaignBuyCount(context) + 1)
                editor.putInt("campaignBuyTotal", getCampaignBuyTotal(context) + price)

                editor.apply()
            }
            1 -> {
                editor.putInt("diaryTotal", getDiaryCount(context) + price)
                editor.apply()
            }
            2 -> {
                editor.putInt("infoTotal", getInfoCount(context) + price)
                editor.apply()
            }
            3 -> {
                editor.putInt("PredictionTotal", getPreCount(context) + price)
                editor.apply()
            }
        }


    }

    private fun getCampaignBuyTotal(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("campaignBuyTotal", 0)
    }

    fun getCampaignBuyCount(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("campaignBuyCount", 0)
    }


    fun getDiaryCount(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("diaryTotal", 0)
    }

    fun getInfoCount(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("infoTotal", 0)
    }

    fun getPreCount(context: Context): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return sharedPreferences.getInt("PredictionTotal", 0)
    }

    fun countPrint(context: Context): Count {

        return Count(
            getUserTotal(context), getCampaignBuyCount(context), getCampaignBuyTotal(context),
            getInfoCount(context), getPreCount(context)
        )
    }

}