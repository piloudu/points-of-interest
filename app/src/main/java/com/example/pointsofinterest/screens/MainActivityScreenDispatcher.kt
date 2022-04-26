package com.example.pointsofinterest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pointsofinterest.ui.components.BottomBar
import com.example.pointsofinterest.ui.components.TopBar
import com.example.pointsofinterest.ui.components.TopDescriptionBar
import com.example.pointsofinterest.view_model.AppState.*
import com.example.pointsofinterest.view_model.MainViewModelInstance

val modifier = Modifier
    .fillMaxSize()
    .background(color = Color.White)

@Composable
fun MainActivityScreenDispatcher(
) {
    val state = MainViewModelInstance.state.collectAsState()
    val cache = state.value.cache

    Box(
        modifier = modifier
    ) {
        Column {
            TopBar()
            TopDescriptionBar(
                text = cache.dataModel.name,
                pois_count = cache.dataModel.poisCount
            )
            when (state.value.innerState) {
                LOADING, MAP -> MainScreen()
                LIST -> TODO()
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            BottomBar()
        }
    }
}