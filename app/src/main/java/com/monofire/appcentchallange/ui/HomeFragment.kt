package com.monofire.appcentchallange.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.adapter.CampaignAdapter
import com.monofire.appcentchallange.db.CampaignHelper
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.listener.CampaignFetchListener
import com.monofire.appcentchallange.model.Campaign
import kotlinx.android.synthetic.main.earn_item_row.*
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaingHelper = CampaignHelper()
        campaingHelper.campaignFetchListener = this

        txtDiary.text = "Günlük 10 altın kazanmak için kalan süre "
        txtBuyNow.setOnClickListener {
            ShareDb.editUserTotal(requireContext(), 10)
            txtTotal.text =
                "Toplam Bakiye: ${ShareDb.getUserTotal(requireContext())}"
            ShareDb.setCount(requireContext(),1,10)
        }
        txt_name.text = "Hoşgeldin ${ShareDb.getUserName(requireContext())} :)"
        txtTotal.text =
            "Toplam Bakiye: ${ShareDb.getUserTotal(requireContext())}"
        categoryInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_earnFragment)
        }
        categoryPrediction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_predictionFragment)
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