package com.example.pointsofinterest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pointsofinterest.utils.LoadingIndicator
import com.example.pointsofinterest.view_model.AppState.*
import com.example.pointsofinterest.view_model.MainViewModelInstance

val modifier = Modifier
    .fillMaxSize()
    .background(color = Color.White)

@Composable
fun MainActivityScreenDispatcher(
) {
    val state by MainViewModelInstance.state.collectAsState()

    Box(
        modifier = modifier
    ) {
        when (state.innerState) {
            LOADING -> LoadingIndicator()
            MAP, LIST -> TODO()
        }
    }
}