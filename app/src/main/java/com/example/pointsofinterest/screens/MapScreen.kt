package com.example.pointsofinterest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.pointsofinterest.MainActivity
import com.example.pointsofinterest.R
import com.example.pointsofinterest.get_data.isEmpty
import com.example.pointsofinterest.ui.components.*
import com.example.pointsofinterest.utils.initialCameraPosition
import com.example.pointsofinterest.utils.toLatLng
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val resources = MainActivity.getContext().resources
    val state = MainViewModelInstance.state.collectAsState()
    val cache = state.value.cache
    val cameraPosition = rememberCameraPositionState { initialCameraPosition }

    Column {
        TopBar()
        TopDescriptionBar(
            text = cache.dataStructure.name,
            pois_count = cache.dataStructure.poisCount
        )
        GoogleMap(
            modifier = modifier.fillMaxSize(),
            cameraPositionState = cameraPosition,
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            properties = MapProperties(mapStyleOptions = MapStyleOptions(
                resources.openRawResource(R.raw.maps_config).bufferedReader().use { it.readText() }
            ))
        ) {
            if (!state.value.cache.dataStructure.isEmpty()) {
                val polygonPoints = cache.dataStructure.coordinates.toLatLng()
                MapPolygon(points = polygonPoints)
                DrawMarkers(cache.dataStructure.pois)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        BottomBar()
    }
}