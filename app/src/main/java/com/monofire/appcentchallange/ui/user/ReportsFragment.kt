package com.monofire.appcentchallange.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.db.ShareDb
import com.monofire.appcentchallange.model.Count
import kotlinx.android.synthetic.main.fragment_reports.*

class ReportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reports: Count = ShareDb.countPrint(requireContext())

        txtCampainCount.text = "${reports.campaign}"

        txtInfoCount.text = "${reports.infoCount}"
        txtPreCount.text = "${reports.preCount}"
        txtDaryCount.text = "${reports.diaryCount}"

        txtCampaignTotal.text = "${reports.totalBuy}"
        txtDiaryTotal.text = "${reports.diaryTotal}"
        txtInfoTotal.text = "${reports.info}"
        txtPreTotal.text = "${reports.pre}"

    }
}