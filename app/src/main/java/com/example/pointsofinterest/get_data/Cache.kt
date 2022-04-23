package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.data_model.DataModel
import com.example.pointsofinterest.data_model.isEmpty
import com.example.pointsofinterest.utils.deserialize
import com.example.pointsofinterest.view_model.MainViewModelInstance

object Cache {
    suspend fun get(): CacheData {
        val cache = MainViewModelInstance.state.value.cache
        return if (cache.dataModel.isEmpty())
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
    val dataModel: DataModel = DataModel.initial()
)