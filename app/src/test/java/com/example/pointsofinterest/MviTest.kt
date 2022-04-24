package com.example.pointsofinterest

import com.example.pointsofinterest.get_data.Cache
import com.example.pointsofinterest.get_data.HttpUrls
import com.example.pointsofinterest.view_model.AppState.*
import com.example.pointsofinterest.view_model.MainActivityState
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModel
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("The Mvi machine sets the state")
class MviTest {
    private lateinit var MainViewModelTestInstance: MainViewModel

    @BeforeEach
    fun init() {
        MainViewModelTestInstance = object : MainViewModel() {}
    }

    @DisplayName("on app init")
    @Test
    fun `init app innerState is LOADING`() {
        val initialExpectedState = MainActivityState.initial()
        val initialState = MainViewModelTestInstance.state.value
        withTestScope {
            initialState shouldBe initialExpectedState
        }
    }

    @DisplayName("on data downloaded")
    @Test
    fun `app innerState is set to MAP after downloading the data`() {
        val url = HttpUrls.MAIN_DATA.string
        withTestScope {
            with(MainViewModelTestInstance) {
                val initialState = state.value
                val expectedState = initialState.copy(
                    innerState = MAP,
                    cache = Cache.get(url)
                )
                sendIntent(MainActivityUserIntent.LoadMap(url))
                val newState = MainViewModelTestInstance.state.value
                newState shouldBe expectedState
            }
        }
    }
}