package com.myproject.qtnapp.ui.favorite

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
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.model.response.Categories
import com.myproject.qtnapp.ui.category.CategoryAdapter

class FavoriteAdapter(val foodList: List<FoodByCategoryEntity>) : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {
    inner class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        var name: TextView = itemView.findViewById(R.id.tv_name_fav)
        var picture: ImageView = itemView.findViewById(R.id.iv_image_fav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food = foodList[position]
        Glide.with(holder.itemView.context)
            .load(food.imageLink)
            .apply(RequestOptions().override(55, 55))
            .into(holder.picture)
        holder.name.text = food.mealName
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}