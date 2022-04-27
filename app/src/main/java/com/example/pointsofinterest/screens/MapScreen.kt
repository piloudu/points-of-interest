package com.example.pointsofinterest.screens

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.LayoutInflaterFactory
import com.example.pointsofinterest.MainActivity
import com.example.pointsofinterest.R
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.ui.components.*
import com.example.pointsofinterest.utils.initialCameraPosition
import com.example.pointsofinterest.utils.toastMessage
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val resources = MainActivity.getContext().resources
    val state = MainViewModelInstance.state.collectAsState()
    val cache = state.value.cache
    val cameraPosition = rememberCameraPositionState { position = initialCameraPosition }
    val coroutineScope = rememberCoroutineScope()

    Column {
        GoogleMap(
            modifier = modifier.weight(1f),
            cameraPositionState = cameraPosition,
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            properties = MapProperties(mapStyleOptions = MapStyleOptions(
                resources.openRawResource(R.raw.maps_config).bufferedReader().use { it.readText() }
            )),
            googleMapOptionsFactory = { GoogleMapOptions(). }
        ) {
            if (!cache.dataModel.isEmpty()) {
                cameraPosition.move(CameraUpdateFactory.newCameraPosition(initialCameraPosition))
                val polygonPoints = cache.dataModel.coordinates
                MapPolygon(points = polygonPoints)
                DrawMarkers(cache.dataModel.pois)
            }
        }
        BottomBar(
            text = "MOSTRAR EN LISTADO",
            onClickAction = {
                if (cache.dataModel.isEmpty())
                    coroutineScope.launch { toastMessage("Descargando datos") }
                else MainViewModelInstance.sendIntent(MainActivityUserIntent.LoadList)
            }
        )
    }
}

class CustomInfoWindowAdapter : GoogleMap.InfoWindowAdapter {
    private val context = MainActivity.getContext()
    private val window: View = LayoutInflater.from(context).inflate(R.layout.custom_popup, null)

    private fun setInfoWindowText(marker: Marker) {
        val title = marker.title
        val tvTitle = window.findViewById<TextView>(R.id.custom_popup)
        if (!TextUtils.isEmpty(title)) {
            tvTitle.text = title
        }
    }

    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View {
        setInfoWindowText(marker)
        return window
    }
}