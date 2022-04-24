package com.example.pointsofinterest.data_model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.android.gms.maps.model.LatLng

data class DataModel(
    val name: String,
    val pois: MutableList<Poi>,
    @JsonAlias("pois_count")
    val poisCount: Int,
    val coordinates: String
) {
    companion object {
        fun initial() = DataModel(
            name = "",
            pois = mutableListOf(),
            poisCount = 0,
            coordinates = ""
        )
    }

    @JsonProperty("pois")
    private fun unpackNested(poisJson: List<Map<String, Any>>) {
        poisJson.forEach {
            pois.add(
                Poi(
                    name = it["name"].toString(),
                    //coordinates = LatLng(it["latitude"] as Double, it["longitude"] as Double),
                    url = getUrlFrom(it),
                    likesCount = it["likes_count"] as Int
                )
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getUrlFrom(poi: Map<String, Any>): String {
        val category: Map<String, Any> = poi["category"] as Map<String, Any>
        val marker: Map<String, Any> = category["marker"] as Map<String, Any>
        return marker["url"].toString()
    }
}

data class Poi(
    //val coordinates: LatLng,
    val name: String,
    val url: String,
    var likesCount: Int
)

fun DataModel.isEmpty() = this == DataModel.initial()