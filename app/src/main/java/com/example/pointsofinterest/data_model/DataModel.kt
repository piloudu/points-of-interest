package com.example.pointsofinterest.data_model

import com.google.gson.annotations.SerializedName

data class DataModel(
    val name: String?,
    val pois: List<Poi>?,
    @SerializedName("pois_count") val poisCount: Int?,
    val coordinates: String?
) {
    companion object {
        fun initial() = DataModel(
            name = "",
            pois = mutableListOf(),
            poisCount = 0,
            coordinates = ""
        )
    }
}

data class Poi(
    val latitude: String,
    val longitude: String,
    val name: String,
    val category: Category,
    var likesCount: Int
)

data class Category(
    val marker: Marker
)

data class Marker(
    val url: String
)

fun DataModel.isEmpty() = this == DataModel.initial()