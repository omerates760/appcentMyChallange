package com.monofire.appcentchallange.listener

import com.monofire.appcentchallange.model.Campaign

interface CampaignFetchListener {
    fun campaingList(list: MutableList<Campaign>)
}