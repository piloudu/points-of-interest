package com.example.pointsofinterest.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.example.pointsofinterest.MainActivity
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.net.URL

inline fun <reified T> String.deserialize(): T = Gson().fromJson(this, T::class.java)

fun withViewModelScope(
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    execute: suspend CoroutineScope.() -> Unit
) = MainViewModelInstance.viewModelScope.launch(dispatcher) { execute() }

suspend fun toastMessage(message: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(MainActivity.getContext(), message, Toast.LENGTH_SHORT).show()
    }
}

const val defaultCameraZoom = 15f
val madridLatLng = LatLng(40.4182777396748, -3.709368076150352)
val initialCameraPosition = CameraPosition.fromLatLngZoom(madridLatLng, defaultCameraZoom)

fun String.toLatLng(): List<LatLng> {
    val latLngList = mutableListOf<LatLng>()
    for (coordinatesGroup in this.split(" ")) {
        val latLngCoordinates = coordinatesGroup.split(",").subList(0, 2)
        latLngList.add(
            LatLng(
                latLngCoordinates[1].toDouble(),
                latLngCoordinates[0].toDouble()
            )
        )
    }
    return latLngList.toList()
}