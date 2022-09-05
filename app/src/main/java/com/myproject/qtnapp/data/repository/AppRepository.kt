package com.myproject.qtnapp.data.repository

import com.myproject.qtnapp.data.local.LocalRepository
import com.myproject.qtnapp.data.local.entity.UserEntity
import com.myproject.qtnapp.data.model.response.CategoriesResponse
import com.myproject.qtnapp.data.remote.RemoteRepository

class AppRepository(private val local: LocalRepository, private val remote: RemoteRepository) {
    fun getCategories() = remote.getCategories()

    fun insertUser(user: UserEntity) = local.insertUser(user)
}