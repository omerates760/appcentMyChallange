package com.monofire.appcentchallange.model

data class Question(
    var questionId: String,
    var question: String,
    var answer: String,
    var answerA: String,
    var answerB: String,
    var answerC: String,
    var answerD: String,
    var questionPrice: Int
)