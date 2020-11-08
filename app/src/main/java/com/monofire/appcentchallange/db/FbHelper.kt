package com.monofire.appcentchallange.db

import android.util.Log
import com.google.firebase.database.*
import com.monofire.appcentchallange.listener.PredictionListener
import com.monofire.appcentchallange.listener.QuestionListener
import com.monofire.appcentchallange.listener.UpdatedUserDataListener
import com.monofire.appcentchallange.model.Campaign
import com.monofire.appcentchallange.model.Prediction
import com.monofire.appcentchallange.model.Question
import com.monofire.appcentchallange.model.User

class FbHelper {
    private val firebaseDatabase: FirebaseDatabase =
        FirebaseDatabase.getInstance()
    var questionListener: QuestionListener? = null
    var updateUserListener: UpdatedUserDataListener? = null
    var predictionListener: PredictionListener? = null
    private var isSuccesful = false
    lateinit var question: Question
    lateinit var prediction: Prediction


    fun getQuestion() {
        firebaseDatabase.getReference("Question")
            .addValueEventListener(object : ValueEventListener {
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

                    }
                    questionListener?.questiongetList(question)

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("hata", "" + error.message)
                }

            })
    }

    fun userUpdateProfile(value: String, option: Int, uid: String) {

        firebaseDatabase.getReference("Users").child(uid).apply {
            when (option) {
                0 -> isSuccesful = child("nickName").setValue(value).isSuccessful
                1 -> isSuccesful = child("email").setValue(value).isSuccessful
                2 -> isSuccesful = child("passWord").setValue(value).isSuccessful
            }

        }
        updateUserListener?.updatedData(value)

    }

    fun getPrediction() {
        firebaseDatabase.getReference("Prediction")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data: DataSnapshot in snapshot.children) {

                        prediction = Prediction(
                            data.child("PredictionId").value.toString(),
                            data.child("PredictionNumber").value.toString().toInt(),
                            data.child("PredictionPrice").value.toString().toInt()
                        )

                    }
                    predictionListener?.getNumber(prediction)

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("hata", "" + error.message)
                }

            })
    }
}