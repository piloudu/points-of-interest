package com.example.pointsofinterest.get_data

import com.google.gson.annotations.SerializedName

data class DeserializedDataStructure(
    val name: String,
    val pois: List<DeserializedPoi>,
    @SerializedName("pois_count") val poisCount: Int,
    val coordinates: String,
)

data class DeserializedPoi(
    val latitude: String,
    val longitude: String,
    val name: String,
    val image: Image,
    val category: Category,
    var likesCount: Int
)

data class Image(
    val url: String
)

data class Category(
    val marker: Marker
)

data class Marker(
    val url: String
)