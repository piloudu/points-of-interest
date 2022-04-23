package com.example.pointsofinterest

import com.example.pointsofinterest.view_model.MainActivityState
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
        val initialState = MainViewModelTestInstance.state
        withTestScope {
            initialState shouldBe initialExpectedState
        }
    }
}