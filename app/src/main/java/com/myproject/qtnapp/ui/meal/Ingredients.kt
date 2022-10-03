package com.myproject.qtnapp.ui.meal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredients(
    val name: String?,
    val moisture: String?
): Parcelable