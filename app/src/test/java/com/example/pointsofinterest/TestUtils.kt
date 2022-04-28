package com.example.pointsofinterest

import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.get_data.*
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

val mockDeserializedPoi = DeserializedPoi(
    latitude = "20",
    longitude = "10",
    description = "poi1",
    id = 5,
    name = "poi name",
    image = Image(url = "image URL"),
    category = Category(
        marker = Marker(
            url = "marker URL"
        )
    ),
    likesCount = 8
)

val mockDeserializedDataStructure = DeserializedDataStructure(
    name = "mockDataStructure",
    deserializedPois = listOf(mockDeserializedPoi),
    poisCount = 1,
    coordinates = "12,17"
)

val mockJson = """
    { "name": "mockDataStructure", "pois_count": 1, "coordinates": "12,17",
     "pois": [{
         "latitude": 20,
         "longitude": 10,
         "description": "poi1",
         "id": 5,
         "name": "poi name",
         "image": { "url": "image URL" },
         "category": { "marker": { "url": "marker URL" } },
         "likes_count": 8
         }]
     }
""".trimIndent()