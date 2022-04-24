package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.Poi
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.utils.deserialize
import com.example.pointsofinterest.view_model.MainViewModelInstance

object Cache {
    suspend fun get(url: String): CacheData {
        val cache = MainViewModelInstance.state.value.cache
        return if (cache.dataModel.isEmpty())
            getNewCache(url)
        else cache
    }

    private suspend fun getNewCache(url: String): CacheData {
        val restResult = RestCall.callFor(url)

        val result = if (restResult.isSuccess())
            CacheData(restResult.message.deserialize())
        else CacheData()
        println("Result: " + (result.dataModel.pois[0].url))
        return result
    }
}

data class CacheData(
    val dataModel: DataModel = DataModel.initial()
)