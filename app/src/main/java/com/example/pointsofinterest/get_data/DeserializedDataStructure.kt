package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.utils.toLatLng
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import java.net.URL

data class DeserializedDataStructure(
    val name: String,
    @SerializedName("pois") val deserializedPois: List<DeserializedPoi>,
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

fun DeserializedDataStructure.toCache(): CacheData {
    val latLngCoordinates: List<LatLng> = coordinates.toLatLng()
    val pois = mutableListOf<Poi>()
    this.deserializedPois.forEach {
        pois.add(
            Poi(
                name = it.name,
                position = LatLng(it.latitude.toDouble(), it.longitude.toDouble()),
                imageUrl = URL(it.image.url.replace("http:", "https:")),
                markerURL = URL(it.category.marker.url.replace("http:", "https:")),
                likesCount = it.likesCount
            )
        )
    }
    return CacheData(
        DataModel(
            name = name,
            pois = pois,
            poisCount = poisCount,
            coordinates = latLngCoordinates
        )
    )
}