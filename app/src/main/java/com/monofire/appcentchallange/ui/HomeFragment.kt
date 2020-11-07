package com.monofire.appcentchallange.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.adapter.CampaignAdapter
import com.monofire.appcentchallange.db.CampaignHelper
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.listener.CampaignFetchListener
import com.monofire.appcentchallange.model.Campaign
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), CampaignFetchListener {
    lateinit var adapter: CampaignAdapter
    lateinit var campaingHelper: CampaignHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaingHelper = CampaignHelper()
        campaingHelper.campaignFetchListener = this
        txt_name.text = "Hoşgeldin ${ShareDb.getUserName(requireContext())} :)"
        txtTotal.text =
            "Toplam Bakiye: ${ShareDb.getUserTotal(requireContext())}"
        //TODO 12saatte bir 10 altın verilecek.Timer yerleştir.
        txtDiary.text="Günlük 10 altın kazanmak için kalan süre "
        txtBuyNow.setOnClickListener {
            //TODO hesaba 10 altın eklenecek
        }
    }

    override fun campaingList(list: MutableList<Campaign>) {
        adapter = CampaignAdapter(list, requireContext())
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerview_buy.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerview_buy.adapter = adapter
    }

}