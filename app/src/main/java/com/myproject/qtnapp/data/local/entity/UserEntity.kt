package com.myproject.qtnapp.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_db")
class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "full_name")
    val fullName: String?,
    @ColumnInfo(name = "email")
    val email: String?,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "birthdate")
    val birthdate: String?,
    @ColumnInfo(name = "new_user")
    val newUser: Boolean
) : Parcelable