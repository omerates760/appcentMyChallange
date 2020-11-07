package com.monofire.appcentchallange

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.monofire.appcentchallange.model.Campaign
import kotlinx.android.synthetic.main.fragment_campaign_detail.*

class CampaignDetailFragment : Fragment() {

    lateinit var dialog: SweetAlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val campaign: Campaign = arguments?.getSerializable("campaingDetails") as Campaign
        Glide.with(view).load(campaign.campaingImage).placeholder(R.drawable.appcentlogo)
            .into(img_campaing)
        txtCampaingDetail.text = campaign.campaignDetail
        txtPrice.text = "Ödenecek Tutar:${campaign.campaingPrice}"

        dialog = SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Kampanya")
            .setConfirmButton(
                "AnaSayfaya Dön"
            ) {
                findNavController().navigateUp()
                dialog.dismiss()
            }
        btn_buy.setOnClickListener {
            dialog.show()
        }

    }


}