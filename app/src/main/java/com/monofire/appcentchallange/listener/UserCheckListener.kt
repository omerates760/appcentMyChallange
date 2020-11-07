package com.monofire.appcentchallange.listener

interface UserCheckListener {
    fun savedUser(isSaved: Boolean)
    fun loggedUser(isLogin: Boolean,errorMessage:String)
}