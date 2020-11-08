package com.monofire.appcentchallange.listener

import com.monofire.appcentchallange.model.Question

interface QuestionListener {
    fun questiongetList(question: Question)
}