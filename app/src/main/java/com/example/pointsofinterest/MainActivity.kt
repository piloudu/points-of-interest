package com.example.pointsofinterest

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.pointsofinterest.screens.CustomInfoWindowAdapter
import com.example.pointsofinterest.screens.ScreensBackbone
import com.example.pointsofinterest.ui.theme.LightGray
import com.example.pointsofinterest.ui.theme.PointsOfInterestTheme
import com.example.pointsofinterest.utils.LockScreenOrientation
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModelInstance
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MainActivity : ComponentActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainViewModelInstance.sendIntent(MainActivityUserIntent.LoadMap())
        appContext = applicationContext
        setContent {
            PointsOfInterestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Set the system top bar color
                    val systemUiController = rememberSystemUiController()
                    systemUiController.setSystemBarsColor(LightGray)

                    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED)
                    ScreensBackbone()
                }
            }
        }
    }

    companion object {
        private lateinit var appContext: Context
        fun getContext() = appContext
        fun isContextInitialized(): Boolean {
            return ::appContext.isInitialized
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.setInfoWindowAdapter(CustomInfoWindowAdapter(applicationContext))
    }
}