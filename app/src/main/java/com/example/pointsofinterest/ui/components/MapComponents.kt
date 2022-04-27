package com.example.pointsofinterest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.ui.theme.Black
import com.example.pointsofinterest.ui.theme.Gothics
import com.example.pointsofinterest.ui.theme.OrangeTransparent
import com.example.pointsofinterest.ui.theme.White
import com.example.pointsofinterest.utils.downloadImage
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

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
    pois: List<Poi>
) {
    pois.forEach { poi ->
        MarkerInfoWindow(
            state = MarkerState(
                position = poi.position
            ),
            icon = BitmapDescriptorFactory.fromBitmap(poi.marker)
        ) {
            Column(
                modifier = Modifier.background(Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = poi.name,
                    letterSpacing = 0.34.sp,
                    color = White,
                    fontSize = 18.sp,
                    fontFamily = Gothics
                )
            }
        }
    }
}