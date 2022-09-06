package com.myproject.qtnapp.data.remote

class RemoteRepository(private val webService: WebService) {

    fun getCategories() = webService.getCategories()
}