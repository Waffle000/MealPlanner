package com.myproject.qtnapp.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.model.response.Categories
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.databinding.ActivityCategoryBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CategoryActivity : AppCompatActivity(), CategoryView, CategoryAdapter.onItemClick {

    private val presenter : CategoryPresenter by inject{
        parametersOf(this)
    }

    private var listCategory: List<Categories> = listOf()

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.getCategories()
    }

    override fun getCategories(data: CategoriesResponse) {
        listCategory = listCategory + data.categories
        setupRV()
    }

    private fun setupRV() {
        with(binding.rvFavoriteCategory) {
            adapter = CategoryAdapter(listCategory, this@CategoryActivity)
            layoutManager = GridLayoutManager(this@CategoryActivity, 2)
        }
    }

    override fun setItemClickChat(data: Categories, position: Int) {

    }
}