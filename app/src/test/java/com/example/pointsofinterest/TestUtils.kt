package com.example.pointsofinterest

import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain

private val dispatcher = TestCoroutineDispatcher()

internal fun withTestScope(scope: suspend CoroutineScope.() -> Unit) {
    runBlocking {
        Dispatchers.setMain(dispatcher)
        scope()
    }
}

val mockPois = listOf(
    Poi(name = "poi1", url = "https://poi1", likesCount = 2),
    Poi(name = "poi2", url = "https://poi2", likesCount = 3),
    Poi(name = "poi3", url = "https://poi3", likesCount = 4)
)

val mockDataModel = DataModel(
    name = "mockPoi", pois = mockPois, poisCount = 3, coordinates = "3,4 5,6 7,8"
)