package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.utils.deserialize
import com.example.pointsofinterest.view_model.MainViewModelInstance

object Cache {
    suspend fun get(): CacheData {
        val cache = MainViewModelInstance.state.value.cache
        return if (cache.pois.isEmpty())
            getNewCache()
        else cache
    }

    private suspend fun getNewCache(): CacheData {
        val restResult = RestCall.callFor(HttpUrls.MAIN_DATA)

        return if (restResult.isSuccess())
            CacheData(restResult.message.deserialize())
        else CacheData()
    }
}

data class CacheData(
    val pois: List<Poi> = emptyList()
)