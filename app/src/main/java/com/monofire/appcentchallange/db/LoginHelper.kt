package com.monofire.appcentchallange.db

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.monofire.appcentchallange.listener.UserCheckListener

class LoginHelper(private val userMail: String, private val userPassword: String) {
    private val session: FirebaseAuth = FirebaseAuth.getInstance()
    var userCheckListener: UserCheckListener? = null

    init {
        userCheckLogin()
    }

    private fun userCheckLogin() {
        if (userMail.isEmpty() || userPassword.isEmpty()) {
            userCheckListener?.loggedUser(false,"Lütfen kutucukları boş bırakmayınız.")
        }else{
            session.signInWithEmailAndPassword(
                userMail,
                userPassword
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    userCheckListener?.loggedUser(true,"Giriş başarılı")

                } else {
                    userCheckListener?.loggedUser(false,"Hata Oluştu")

                }

            }
        }

    }
}