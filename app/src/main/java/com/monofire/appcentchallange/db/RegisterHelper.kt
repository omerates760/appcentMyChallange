package com.monofire.appcentchallange.db

import android.util.Log
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.monofire.appcentchallange.listener.RegisterCheckListener
import com.monofire.appcentchallange.model.User

class RegisterHelper(private val user: User) {
    private var isCheck = false
    private val session = FirebaseAuth.getInstance()
    var registerCheckListener:RegisterCheckListener?=null

    fun userInputisCheck(): Boolean {

        if (user.nickName.isEmpty() || user.eMail.isEmpty() || user.passWord.isEmpty()) {
            isCheck = false
        } else {
            isCheck = (Patterns.EMAIL_ADDRESS.matcher(user.eMail).matches())
        }
        return isCheck
    }

    fun userSaveData() {
        session.createUserWithEmailAndPassword(user.eMail, user.passWord)
            .addOnCompleteListener {
                isCheck = it.isSuccessful
                user.userId = session.currentUser!!.uid
                FirebaseDatabase.getInstance().getReference("Users").child(user.userId).setValue(user)
                    .addOnCompleteListener {
                        registerCheckListener?.saveUser(true)
                        Log.e("sonuc", "başarılı")
                    }

            }
    }


}