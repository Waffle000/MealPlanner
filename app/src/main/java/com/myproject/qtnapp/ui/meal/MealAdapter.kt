package com.myproject.qtnapp.ui.meal

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

class MealAdapter(val listIngredients: MutableList<Ingredients?>) :
    RecyclerView.Adapter<MealAdapter.ListViewHolder>() {
    inner class ListViewHolder(listView: View) : RecyclerView.ViewHolder(listView) {
        var name: TextView = itemView.findViewById(R.id.tv_ingredients)
        var picture: ImageView = itemView.findViewById(R.id.iv_ingredients)
        var moisture: TextView = itemView.findViewById(R.id.tv_moisture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = listIngredients[position]
        Glide.with(holder.itemView.context)
            .load("https://www.themealdb.com/images/ingredients/${list?.name}.png")
            .apply(RequestOptions().override(35, 35))
            .into(holder.picture)
        holder.moisture.text = list?.moisture
        holder.name.text = list?.name
    }

    override fun getItemCount(): Int {
        return listIngredients.size
    }
}