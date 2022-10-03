package com.myproject.qtnapp.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import java.text.SimpleDateFormat

class HistoryAdapter(val historyList: List<HistoryEntity>) : RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {
    inner class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        var date: TextView = itemView.findViewById(R.id.tv_date_history)
        var pro: TextView = itemView.findViewById(R.id.tv_pro_history)
        var carb: TextView = itemView.findViewById(R.id.tv_carb_history)
        var fat: TextView = itemView.findViewById(R.id.tv_fat_history)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val history = historyList[position]
        if(!history.date.equals("")) {
            holder.date.text = SimpleDateFormat("dd MMM yyyy").format(SimpleDateFormat("dd/MM/yyyy").parse(history.date))
        }
        holder.pro.text = history.pro.toString()
        holder.carb.text = history.carb.toString()
        holder.fat.text = history.fat.toString()
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}