package com.monofire.appcentchallange.model

data class User(var userId: String, val nickName: String, val eMail: String, val passWord: String) {

    constructor(nickName: String, eMail: String, passWord: String) : this(
        "",
        nickName,
        eMail,
        passWord
    ) {
    }
}
