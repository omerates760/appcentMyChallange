package com.monofire.appcentchallange.model

data class User(
    var userId: String,
    val nickName: String,
    val eMail: String,
    val passWord: String,
    val total: Int
) {

    constructor(nickName: String, eMail: String, passWord: String, total: Int) : this(
        "",
        nickName,
        eMail,
        passWord, total
    )
    constructor(total: Int) : this(
        "",
        "",
        "",
        "", total
    )
}
