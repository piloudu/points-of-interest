package com.example.pointsofinterest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.ui.components.BottomBar
import com.example.pointsofinterest.ui.components.TopBar
import com.example.pointsofinterest.ui.components.TopDescriptionBar
import com.example.pointsofinterest.utils.initialCameraPosition
import com.example.pointsofinterest.utils.madridLatLng
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val state = MainViewModelInstance.state.collectAsState()
    val cameraPosition = rememberCameraPositionState { initialCameraPosition }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPosition
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