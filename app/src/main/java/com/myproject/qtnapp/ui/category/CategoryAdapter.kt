package com.myproject.qtnapp.ui.category

import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.model.response.Categories
import com.myproject.qtnapp.data.model.response.CategoriesResponse

class CategoryAdapter(val listCategory : List<Categories>, private var clickListener : onItemClick) : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {
    inner class ListViewHolder(listView : View) : RecyclerView.ViewHolder(listView) {
        var nameCategory : TextView = itemView.findViewById(R.id.tv_item_category)
        var picture : ImageView = itemView.findViewById(R.id.iv_item_category)
        var check: ImageView = itemView.findViewById(R.id.iv_check_category)
        fun itemClick(data: Categories, action : onItemClick) {
            itemView.setOnClickListener{
                check.isGone = !check.isGone
                action.setItemClickChat(data, adapterPosition, !check.isGone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val category = listCategory[position]
        Glide.with(holder.itemView.context)
            .load(category.strCategoryThumb)
            .apply(RequestOptions().override(55, 55))
            .into(holder.picture)
        holder.nameCategory.text = category.strCategory
        holder.itemClick(category, clickListener)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    interface onItemClick {
        fun setItemClickChat(data: Categories, position: Int, check: Boolean)
    }
}
