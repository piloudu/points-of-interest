package com.example.pointsofinterest.data_model

import com.fasterxml.jackson.annotation.JsonAlias

data class DataModel(
    val name: String,
    val pois: List<Poi>,
    @JsonAlias("pois_count")
    val poisCount: Int,
    val coordinates: String
) {
    companion object {
        fun initial() = DataModel(
            name = "",
            pois = emptyList(),
            poisCount = 0,
            coordinates = ""
        )
    }
}

data class Poi(
    val name: String,
    val url: String,
    val likesCount: Int
)

fun DataModel.isEmpty() = this != DataModel.initial()