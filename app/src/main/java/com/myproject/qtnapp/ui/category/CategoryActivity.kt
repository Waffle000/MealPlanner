package com.myproject.qtnapp.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.qtnapp.R
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.Categories
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.databinding.ActivityCategoryBinding
import com.myproject.qtnapp.di.SharedPreference
import com.myproject.qtnapp.ui.navi.NavigationActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CategoryActivity : AppCompatActivity(), CategoryView, CategoryAdapter.onItemClick {

    private val presenter : CategoryPresenter by inject{
        parametersOf(this)
    }

    private val category: MutableSet<String> = mutableSetOf()

    private var listCategory: List<Categories> = listOf()

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.getCategories()
        init()
    }

    private fun init() {
        val userData = intent.getParcelableExtra<UserEntity>(USER_DATA)
        binding.btnSubmitCategory.setOnClickListener{
            val sharedPreference = SharedPreference(this)
            sharedPreference.categoryData("category", category)
            userData?.newUser = false
            if (userData != null) {
                presenter.updateUser(userData)
            }
            startActivity(Intent(this, NavigationActivity::class.java))
        }
    }

    override fun getCategories(data: CategoriesResponse) {
        listCategory = listCategory + data.categories
        setupRV()
    }

    override fun updateUser(success: Boolean) {

    }

    private fun setupRV() {
        with(binding.rvFavoriteCategory) {
            adapter = CategoryAdapter(listCategory, this@CategoryActivity)
            layoutManager = GridLayoutManager(this@CategoryActivity, 2)
        }
    }


    override fun setItemClickChat(data: Categories, position: Int, check: Boolean) {
        if(check) {
            category.add(data.strCategory ?: "")
        } else {
            category.remove(data.strCategory ?: "")
        }
    }

    companion object {
        val USER_DATA = "CategoryActivity.UserData"
    }
}