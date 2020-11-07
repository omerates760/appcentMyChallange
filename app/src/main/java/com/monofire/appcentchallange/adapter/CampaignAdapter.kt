package com.monofire.appcentchallange.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monofire.appcentchallange.R
import com.monofire.appcentchallange.model.Campaign
import kotlinx.android.synthetic.main.item_buy_row.view.*

class CampaignAdapter(val list: MutableList<Campaign>, private val context: Context) :
    RecyclerView.Adapter<CampaignAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_buy_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(list[position].campaingImage).placeholder(R.drawable.appcentlogo)
            .into(holder.img)
        holder.price.text = list[position].campaingPrice.toString()

    }

    override fun getItemCount(): Int = list.size
    class MyViewHolder(holder: View) : RecyclerView.ViewHolder(holder) {
        val img = holder.findViewById<ImageView>(R.id.imgCampaignId)
        val price = holder.findViewById<TextView>(R.id.campaignPrice)
    }
}