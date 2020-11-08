package com.monofire.appcentchallange

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.monofire.appcentchallange.db.FbHelper
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.listener.QuestionListener
import com.monofire.appcentchallange.model.Question
import com.monofire.appcentchallange.util.DateConvert
import kotlinx.android.synthetic.main.fragment_earn.*
import java.text.SimpleDateFormat


class EarnFragment : Fragment(), View.OnClickListener, QuestionListener {
    lateinit var timer: CountDownTimer
    private var totalTime: Long = 20000
    private lateinit var fbHelper: FbHelper
    private lateinit var question: Question
    private var isCorrect: Boolean = false
    private lateinit var currentTime: TextView
    private val dateConvert = DateConvert()
    private var waitNewQuestionTime: Long = 0
    private var isNewQuesion = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_earn, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbHelper = FbHelper()
        fbHelper.questionListener = this
        fbHelper.getQuestion()

        ShareDb.setInfoCategoryCurrentTime(requireContext(), System.currentTimeMillis())
        waitNewQuestionTime = ShareDb.getInfoCategoryCurrentTime(requireContext())


        val total = (waitNewQuestionTime + 43200000)
        if (total > System.currentTimeMillis()) {
            alertType(
                SweetAlertDialog.WARNING_TYPE,
                "Bilgilendirme",
                "Soru için verilen süre 20 saniyedir.",
                "Başla"
            )
        } else {
            alertType(
                SweetAlertDialog.ERROR_TYPE,
                "Bilgilendirme",
                "Yeni soru saat ${dateConvert.getDate(waitNewQuestionTime + 43200000)} da yayınlanacaktır.",
                "Ana sayfaya Dön"
            )
            isNewQuesion = true

        }

        view.visibility = View.GONE
        currentTime = view.findViewById(R.id.txtCurrentTime)

        answerA.setOnClickListener(this)
        answerB.setOnClickListener(this)
        answerC.setOnClickListener(this)
        answerD.setOnClickListener(this)

    }

    private fun alertType(type: Int, title: String, subTitle: String, btnText: String) {
        SweetAlertDialog(requireContext(), type)
            .setTitleText(title)
            .setContentText(subTitle)
            .setConfirmButton(btnText) {
                timerSet()
                it.cancel()
                if (view?.visibility == View.GONE) view?.visibility = View.VISIBLE
                if (isCorrect || isNewQuesion) findNavController().navigateUp()

            }.show()

    }

    private fun timerSet() {
        timer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(p0: Long) {
                val mTime = SimpleDateFormat("mm:ss")
                currentTime.text = "Kalan süre: ${mTime.format(p0)}"
            }

            override fun onFinish() {
                isCorrect = true
                alertType(
                    SweetAlertDialog.ERROR_TYPE,
                    "Süre bitti",
                    "12 saat sonra tekrar yeniyi soruyu görebilirisin.",
                    "Ana sayfaya Dön"
                )
            }

        }.start()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            answerA.id -> {
                checkAnswer(answerA.text.toString())
            }
            answerB.id -> {
                checkAnswer(answerB.text.toString())
            }
            answerC.id -> {
                checkAnswer(answerC.text.toString())
            }
            answerD.id -> {
                checkAnswer(answerD.text.toString())
            }
        }
    }

    private fun checkAnswer(text: String) {
        timer.cancel()
        isCorrect = true
        when (text) {
            question.answer -> {
                alertType(
                    SweetAlertDialog.SUCCESS_TYPE,
                    "Tebrikler",
                    "Soruyu doğru bildiniz. Bu sayede ${question.questionPrice} altın kazandınız",
                    "Ana sayfaya Dön"
                )
                ShareDb.editUserTotal(requireContext(), question.questionPrice)
                ShareDb.setCount(requireContext(), 2, question.questionPrice)

            }
            else -> {
                alertType(
                    SweetAlertDialog.ERROR_TYPE,
                    "Kaybettiniz",
                    "12 saat sonra yeni soru için mutlaka gel :)",
                    "Ana sayfaya Dön"
                )
            }
        }
    }

    override fun questiongetList(question: Question) {
        this.question = question
        answerA.text = question.answerA
        answerB.text = question.answerB
        answerC.text = question.answerC
        answerD.text = question.answerD

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

}