package com.monofire.appcentchallange

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.model.Campaign
import kotlinx.android.synthetic.main.fragment_campaign_detail.*

class CampaignDetailFragment : Fragment() {

    lateinit var campaign: Campaign
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_campaign_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        campaign= arguments?.getSerializable("campaingDetails") as Campaign
        Glide.with(view).load(campaign.campaingImage).placeholder(R.drawable.appcentlogo)
            .into(img_campaing)
        txtCampaingDetail.text = campaign.campaignDetail
        txtPrice.text = "Ödenecek Tutar:${campaign.campaingPrice}"


        btn_buy.setOnClickListener {
            if (ShareDb.getUserTotal(requireContext()).toString()
                    .toInt() > campaign.campaingPrice.toInt()
            ) {
                campaignBuyCheck(true)
            } else {
                campaignBuyCheck(false)
            }

        }

    }

    private fun campaignBuyCheck(isBuy: Boolean) {
        when (isBuy) {
            true -> {
                alertType(
                    SweetAlertDialog.SUCCESS_TYPE,
                    "Ödeme Başarılı,",
                    "Kampanya dahilinde 24 saat geçerli olacaktır."
                )
                ShareDb.editUserTotal(requireContext(), -campaign.campaingPrice)
            }
            false -> {
                alertType(SweetAlertDialog.ERROR_TYPE, "Ödeme Başarısız", "Bakiyeniz yetersizdir.")
            }

        }
    }

    private fun alertType(type: Int, title: String, subTitle: String) {
        SweetAlertDialog(requireContext(), type)
            .setTitleText(title)
            .setContentText(subTitle)
            .setConfirmButton(
                "AnaSayfaya Dön"
            ) {

                findNavController().navigateUp()
                it.cancel()
            }.show()

    }


}