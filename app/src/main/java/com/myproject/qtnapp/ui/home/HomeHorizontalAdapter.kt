package com.myproject.qtnapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity

class HomeHorizontalAdapter(val listFood: List<FoodByCategoryEntity>, private var clickListener : onItemClick) : RecyclerView.Adapter<HomeHorizontalAdapter.ListViewHolder>() {
    inner class ListViewHolder(listView : View) : RecyclerView.ViewHolder(listView) {
        var nameFood : TextView = itemView.findViewById(R.id.tv_food_item_home)
        var imageFood: ImageView = itemView.findViewById(R.id.iv_food_item_home)
        fun itemClick(data: FoodByCategoryEntity, action : onItemClick) {
            itemView.setOnClickListener{
                action.setItemClickFood(data, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_home_horizontal, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food = listFood[position]
        holder.nameFood.text = food.mealName
        Glide.with(holder.itemView.context)
            .load(food.imageLink)
            .into(holder.imageFood)
        holder.itemClick(food, clickListener)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    interface onItemClick {
        fun setItemClickFood(data: FoodByCategoryEntity, position: Int)
    }

}