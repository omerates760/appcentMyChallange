package com.monofire.appcentchallange.db

import android.util.Log
import com.google.firebase.database.*
import com.monofire.appcentchallange.listener.QuestionListener
import com.monofire.appcentchallange.model.Campaign
import com.monofire.appcentchallange.model.Question
import com.monofire.appcentchallange.model.User

class FbHelper {
    private val firebaseDatabase: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Question")
    var questionListener: QuestionListener? = null
    lateinit var question: Question


    fun getQuestion() {
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data: DataSnapshot in snapshot.children) {

                    question = Question(
                        data.child("QuestionId").value.toString(),
                        data.child("Question").value.toString(),
                        data.child("Answer").value.toString(),
                        data.child("AnswerA").value.toString(),
                        data.child("AnswerB").value.toString(),
                        data.child("AnswerC").value.toString(),
                        data.child("AnswerD").value.toString(),
                        data.child("QuestionPrice").value.toString().toInt()
                    )
                    Log.e("soruuu",""+data.child("QuestionId").value.toString())


                }
                questionListener?.questiongetList(question)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("hata",""+error.message)
            }

        })
    }
    fun userUpdateProfile(
        user: User,
    ) {
        ref.child("Users").child(user.userId).apply {
            child("email").setValue(user.eMail)
            child("nickName").setValue(user.eMail)
            child("passWord").setValue(user.eMail)
        }
    }
}