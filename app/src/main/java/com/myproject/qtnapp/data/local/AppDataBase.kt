package com.myproject.qtnapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproject.qtnapp.data.local.dao.ExampleDao
import com.myproject.qtnapp.data.local.dao.UserDao
import com.myproject.qtnapp.data.local.entity.ExampleEntity
import com.myproject.qtnapp.data.local.entity.UserEntity

@Database(
    entities = [
        ExampleEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    abstract fun userDao(): UserDao
}