package com.example.pointsofinterest.ui.components

import androidx.compose.runtime.Composable
import com.example.pointsofinterest.get_data.DeserializedPoi
import com.example.pointsofinterest.ui.theme.OrangeTransparent
import com.example.pointsofinterest.ui.theme.White
import com.example.pointsofinterest.utils.downloadImage
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import java.net.URL

@Composable
fun MapPolygon(
    points: List<LatLng>
) {
    Polygon(
        points,
        fillColor = OrangeTransparent,
        strokePattern = listOf(Dot()),
        strokeColor = White,
        strokeWidth = 10f
    )
}

@Composable
fun DrawMarkers(
    pois: List<DeserializedPoi>
) {
    pois.forEach {
        val position = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
        val url = URL(it.category.marker.url.replace("http", "https"))
        Marker(
            state = MarkerState(
                position = position
            ),
            icon = BitmapDescriptorFactory.fromBitmap(url.downloadImage())
        )
    }
}