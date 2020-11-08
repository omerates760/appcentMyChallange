package com.monofire.appcentchallange.listener

import com.monofire.appcentchallange.model.Prediction


interface PredictionListener {
    fun getNumber(prediction: Prediction)
}