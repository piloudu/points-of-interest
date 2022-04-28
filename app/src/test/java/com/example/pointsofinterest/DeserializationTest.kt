package com.example.pointsofinterest

import com.example.pointsofinterest.get_data.DeserializedDataStructure
import com.example.pointsofinterest.utils.deserialize
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeserializationTest {
    @get:Rule
    val deserializedDataStructure = mockJson.deserialize<DeserializedDataStructure>()

    @DisplayName("Deserialization behaves as expected")
    @Test
    fun `deserialized JSON picks de general data properly`() {
        deserializedDataStructure shouldBe mockDeserializedDataStructure
    }
}