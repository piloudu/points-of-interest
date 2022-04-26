package com.example.pointsofinterest.data_model

import com.example.pointsofinterest.get_data.DeserializedDataStructure
import com.google.android.gms.maps.model.LatLng
import java.net.URL

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
    val position: LatLng,
    val imageUrl: URL,
    val markerURL: URL,
    val likesCount: Int
)

fun DataModel.isEmpty() = this == DataModel.initial()
