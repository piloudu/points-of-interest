package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.utils.downloadImage
import com.example.pointsofinterest.utils.toLatLng
import com.example.pointsofinterest.utils.toastMessage
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
    val description: String,
    val id: Int,
    val name: String,
    val image: Image,
    val category: Category,
    @SerializedName("likes_count") val likesCount: Int
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

suspend fun DeserializedDataStructure.toCache(): CacheData {
    val latLngCoordinates: List<LatLng> = coordinates.toLatLng()
    val pois = mutableListOf<Poi>()
    this.deserializedPois.forEach {
        val image = URL(it.image.url).downloadImage()
        val marker = URL(it.category.marker.url).downloadImage()
        pois.add(
            Poi(
                name = it.name,
                description = it.description,
                position = LatLng(it.latitude.toDouble(), it.longitude.toDouble()),
                id = it.id,
                image = image,
                marker = marker,
                likesCount = it.likesCount
            )
        )
    }
    toastMessage("Datos descargados")
    return CacheData(
        DataModel(
            name = name,
            pois = pois,
            poisCount = poisCount,
            coordinates = latLngCoordinates
        )
    )
}