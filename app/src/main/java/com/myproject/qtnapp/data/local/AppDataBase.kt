package com.myproject.qtnapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproject.qtnapp.data.local.dao.ExampleDao
import com.myproject.qtnapp.data.local.dao.FoodByCategoryDao
import com.myproject.qtnapp.data.local.dao.HistoryDao
import com.myproject.qtnapp.data.local.dao.UserDao
import com.myproject.qtnapp.data.local.entity.ExampleEntity
import com.myproject.qtnapp.data.local.entity.FoodByCategoryEntity
import com.myproject.qtnapp.data.local.entity.HistoryEntity
import com.myproject.qtnapp.data.local.entity.UserEntity

@Database(
    entities = [
        ExampleEntity::class,
        UserEntity::class,
        FoodByCategoryEntity::class,
        HistoryEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    abstract fun userDao(): UserDao
    abstract fun foodByCategoryDao(): FoodByCategoryDao
    abstract fun historyDao(): HistoryDao
}