package com.myproject.qtnapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.model.response.Categories
import com.myproject.qtnapp.ui.category.CategoryAdapter

class HomeAdapter(val listCategory: MutableSet<String>) : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    inner class ListViewHolder(listView : View) : RecyclerView.ViewHolder(listView) {
        var nameCategory : TextView = itemView.findViewById(R.id.tv_title_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val category = listCategory.elementAt(position)
        holder.nameCategory.text = category
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }
}
