package com.myproject.qtnapp.data.model.response

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("categories" ) var categories : List<Categories>
)

data class Categories (

    @SerializedName("idCategory"             ) var idCategory             : String? = null,
    @SerializedName("strCategory"            ) var strCategory            : String? = null,
    @SerializedName("strCategoryThumb"       ) var strCategoryThumb       : String? = null,
    @SerializedName("strCategoryDescription" ) var strCategoryDescription : String? = null

)