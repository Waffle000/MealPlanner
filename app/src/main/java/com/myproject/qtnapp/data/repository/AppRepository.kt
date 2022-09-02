package com.myproject.qtnapp.data.repository

import com.myproject.qtnapp.data.local.LocalRepository
import com.myproject.qtnapp.data.remote.RemoteRepository

class AppRepository(private val local: LocalRepository, private val remote: RemoteRepository) {
}