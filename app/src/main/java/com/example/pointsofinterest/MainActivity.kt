package com.example.pointsofinterest

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.pointsofinterest.screens.MainActivityScreenDispatcher
import com.example.pointsofinterest.ui.theme.PointsOfInterestTheme
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModelInstance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainViewModelInstance.sendIntent(MainActivityUserIntent.LoadMap)
        appContext = applicationContext
        setContent {
            PointsOfInterestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityScreenDispatcher()
                }
            }
        }
    }

    companion object {
        private lateinit var appContext: Context
        fun getContext() = appContext
    }
}