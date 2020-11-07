package com.monofire.appcentchallange.model

import java.io.Serializable

class Campaign(
    val campaignId: String,
    val campaingImage: String,
    val campaingPrice: Int,
    val campaignDetail: String
) :
    Serializable
