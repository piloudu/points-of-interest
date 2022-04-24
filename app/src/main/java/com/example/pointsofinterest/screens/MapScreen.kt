package com.example.pointsofinterest.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.utils.initialCameraPosition
import com.example.pointsofinterest.utils.madridLatLng
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val state = MainViewModelInstance.state.collectAsState()
    val cameraPosition = rememberCameraPositionState { initialCameraPosition }
    GoogleMap(
        Modifier.fillMaxSize(),
        cameraPositionState = cameraPosition
    ) {
        if (!state.value.cache.dataModel.isEmpty())
            Marker(
                state = MarkerState(position = madridLatLng)
            )
    }
}