package com.myproject.qtnapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproject.qtnapp.data.local.dao.ExampleDao
import com.myproject.qtnapp.data.local.entity.ExampleEntity

@Database(
    entities = [
        ExampleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}