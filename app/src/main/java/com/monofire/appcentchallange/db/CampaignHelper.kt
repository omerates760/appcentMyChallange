package com.monofire.appcentchallange.db

import android.util.Log
import com.google.firebase.database.*
import com.monofire.appcentchallange.listener.CampaignFetchListener
import com.monofire.appcentchallange.model.Campaign

class CampaignHelper {
    private val firebaseDatabase: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Campaigns")
    private var campaignList = mutableListOf<Campaign>()
    var campaignFetchListener: CampaignFetchListener? = null

    init {
        getCampaignList()
    }

    private fun getCampaignList() {
        firebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data: DataSnapshot in snapshot.children) {

                    val campaign = Campaign(
                        data.child("campaignsId").value.toString(),
                        data.child("campaignsImage").value.toString(),
                        data.child("campaignsPrice").value.toString().toInt()
                    )
                    campaignList.add(campaign)
                }
                campaignFetchListener?.campaingList(campaignList)
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}