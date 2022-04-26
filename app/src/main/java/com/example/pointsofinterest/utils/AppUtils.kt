package com.example.pointsofinterest.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.example.pointsofinterest.MainActivity
import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun <reified T> String.deserialize(): T = Gson().fromJson(this, T::class.java)

fun withViewModelScope(
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    execute: suspend CoroutineScope.() -> Unit
) = MainViewModelInstance.viewModelScope.launch(dispatcher) { execute() }

fun toastMessage(message: String) {
    Toast.makeText(MainActivity.getContext(), message, Toast.LENGTH_SHORT).show()
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

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // get drawable from resources and create e Bitmap for it
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // create a BitmapDescriptor with it
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}