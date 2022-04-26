package com.example.pointsofinterest.data_model

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng

data class DataModel(
    val name: String,
    val pois: List<Poi>,
    val poisCount: Int,
    val coordinates: List<LatLng>
) {
    companion object {
        fun initial() = DataModel(
            name = "",
            pois = emptyList(),
            poisCount = 0,
            coordinates = emptyList()
        )
    }
}

data class Poi(
    val name: String,
    val id: Int,
    val position: LatLng,
    val image: Bitmap,
    val marker: Bitmap,
    val likesCount: Int
)

fun DataModel.isEmpty() = this == DataModel.initial()