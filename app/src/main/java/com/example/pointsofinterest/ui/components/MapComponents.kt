package com.example.pointsofinterest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.ui.theme.*
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
            PopupInfoContent(
                poiName = poi.name,
                location = poi.description
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopupInfoContent(
    poiName: String = "MISSION OF BURMA EN CONCIERTO",
    location: String = "Sala La Riviera",
    time: String = "22:00 - "
) {

    Box {
        Column(
            modifier = Modifier
                .width(166.dp)
                .background(Black)
                .padding(start = 16.dp, end = 16.dp, top = 17.dp, bottom = 10.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = poiName,
                textAlign = TextAlign.Center,
                letterSpacing = 0.34.sp,
                color = White,
                fontSize = 18.sp,
                fontFamily = Gothics
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = time,
                    letterSpacing = 0.19.sp,
                    fontSize = 10.sp,
                    color = LightGray,
                    fontFamily = RobotoRegular
                )
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    modifier = Modifier
                        .height(14.dp)
                        .width(8.dp),
                    painter = painterResource(id = R.drawable.ic_map_marker),
                    contentDescription = "map marker",
                    colorFilter = ColorFilter.tint(Orange)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = location,
                    letterSpacing = 0.19.sp,
                    fontSize = 10.sp,
                    color = Orange,
                    fontFamily = RobotoBold
                )
            }
        }
    }
}