package com.example.pointsofinterest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.ui.components.BottomBar
import com.example.pointsofinterest.ui.components.ListElement
import com.example.pointsofinterest.ui.components.ListHeader
import com.example.pointsofinterest.view_model.MainActivityState
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModelInstance

@Preview(showBackground = true)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    state: MainActivityState = MainActivityState.initial()
) {
    val pois = state.cache.dataModel.pois
    Column {
        ListHeader(selectedSortingOption = state.sortingOption)
        LazyColumn(Modifier.weight(1f)) {
            item {
            }
            itemsIndexed(pois) { _: Int, poi: Poi ->
                with(poi) {
                    ListElement(
                        imageId = id,
                        image = image,
                        likesCount = likesCount,
                        text = name
                    )
                }
            }
        }
        BottomBar(
            text = "MOSTRAR EN MAPA",
            onClickAction = {
                MainViewModelInstance.sendIntent(MainActivityUserIntent.LoadMap())
            }
        )
    }
}