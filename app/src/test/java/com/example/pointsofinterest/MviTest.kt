package com.example.pointsofinterest

import com.example.pointsofinterest.view_model.MainActivityState
import com.example.pointsofinterest.view_model.MainActivityUserIntent
import com.example.pointsofinterest.view_model.MainViewModel
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("The Mvi machine sets the state")
class MviTest {
    lateinit var MainViewModelTestInstance: MainViewModel

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
    fun `app state after downloading data is MAP`() {
        withTestScope {
            with(MainViewModelTestInstance) {
                sendIntent(MainActivityUserIntent.LoadMap)
                val state = state.value
                val cache = Cache.get()
                val expectedState = state.copy(
                    cache = cache
                )
                state shouldBe expectedState
            }
        }
    }
}