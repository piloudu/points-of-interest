package com.example.pointsofinterest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.pointsofinterest.MainActivity
import com.example.pointsofinterest.R
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.ui.components.BottomBar
import com.example.pointsofinterest.ui.components.TopBar
import com.example.pointsofinterest.ui.components.TopDescriptionBar
import com.example.pointsofinterest.utils.initialCameraPosition
import com.example.pointsofinterest.utils.madridLatLng
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val resources = MainActivity.getContext().resources
    val state = MainViewModelInstance.state.collectAsState()
    val cameraPosition = rememberCameraPositionState { initialCameraPosition }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPosition,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        properties = MapProperties(mapStyleOptions = MapStyleOptions(
            resources.openRawResource(R.raw.maps_config).bufferedReader().use { it.readText() }
        ))
    ) {
        if (!state.value.cache.dataModel.isEmpty())
            Marker(
                state = MarkerState(position = madridLatLng)
            )
    }
    Column {
        TopBar()
        TopDescriptionBar()
    }
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        BottomBar()
    }
}