package com.myproject.qtnapp.data.remote

class RemoteRepository(private val webService: WebService) {

    suspend fun getCategories() = webService.getCategories().body()
}