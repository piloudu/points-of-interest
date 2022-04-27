package com.example.pointsofinterest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.ui.components.BottomBar
import com.example.pointsofinterest.ui.components.TopBar
import com.example.pointsofinterest.ui.components.TopDescriptionBar
import com.example.pointsofinterest.utils.toastMessage
import com.example.pointsofinterest.view_model.AppState.*
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModelInstance
import kotlinx.coroutines.launch

val modifier = Modifier
    .fillMaxSize()
    .background(color = Color.White)

@Composable
fun ScreensBackbone(
) {
    val state = MainViewModelInstance.state.collectAsState()
    val cache = state.value.cache
    val innerState = state.value.innerState
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
    ) {
        Column {
            TopBar(
                backAction = {
                    if (innerState == LIST)
                        MainViewModelInstance.sendIntent(MainActivityUserIntent.LoadMap())
                }
            )
            TopDescriptionBar(
                text = cache.dataModel.name,
                pois_count = cache.dataModel.poisCount,
            )
            when (innerState) {
                LOADING, MAP -> MainScreen(modifier = Modifier.weight(1f))
                LIST -> ListScreen(
                    modifier = Modifier.weight(1f),
                    dataModel = cache.dataModel
                )
            }
        }
        if (innerState == LOADING || innerState == MAP) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
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
    }
}