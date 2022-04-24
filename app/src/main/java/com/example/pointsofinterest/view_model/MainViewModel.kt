package com.example.pointsofinterest.view_model

import com.example.pointsofinterest.get_data.Cache
import com.example.pointsofinterest.get_data.CacheData
import com.example.pointsofinterest.get_data.HttpUrls
import com.example.pointsofinterest.utils.withViewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

/**
 * MainViewModel is not made a singleton in order for tests to be able to instance a new object
 * for each case
 */
abstract class MainViewModel : BaseViewModel<MainActivityState, MainActivityUserIntent>() {
    private val reducer = MainReducer(MainActivityState.initial())

    override val state: StateFlow<MainActivityState> = reducer.state

    fun sendIntent(userIntent: MainActivityUserIntent) {
        withViewModelScope(Dispatchers.Default) {
            userIntent.action()
        }
        reducer.sendIntent(userIntent)
    }


    private class MainReducer(initialState: MainActivityState) :
        Reducer<MainActivityState, MainActivityUserIntent>(initialState) {
        override fun reduce(oldState: MainActivityState, userIntent: MainActivityUserIntent) {
            withViewModelScope {
                when (userIntent) {
                    is MainActivityUserIntent.LoadMap -> setMapState(oldState, userIntent.url)
                }
            }
        }

        private fun setMapState(oldState: MainActivityState, url: String) {
            lateinit var cache: CacheData
            withViewModelScope(Dispatchers.IO) {
                cache = Cache.get(url)
            }.invokeOnCompletion {
                setState(
                    oldState.copy(
                        innerState = AppState.MAP,
                        cache = cache
                    )
                )
            }
        }
    }
}

/**
 * Create a MainViewModel instance to be used in the whole app
 */
object MainViewModelInstance : MainViewModel()

sealed class MainActivityUserIntent : UserIntent {
    class LoadMap(val url: String = HttpUrls.MAIN_DATA.string) : MainActivityUserIntent()
}

data class MainActivityState(
    val innerState: AppState,
    val cache: CacheData
) : UiState {
    companion object {
        fun initial() = MainActivityState(
            innerState = AppState.LOADING,
            cache = CacheData()
        )
    }
}

enum class AppState {
    LOADING, MAP, LIST
}