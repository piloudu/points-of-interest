package com.example.map.resource.pointsofinterest.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.map.resource.pointsofinterest.ui.theme.Purple700

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = Purple700
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(screenHeight / 3))
        CircularProgressIndicator(
            modifier = Modifier
                .width(screenWidth / 2)
                .height(screenHeight / 3),
            color = color
        )
        Spacer(Modifier.height(screenHeight / 3))
    }
}