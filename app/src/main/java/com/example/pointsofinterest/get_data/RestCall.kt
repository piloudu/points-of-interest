package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.utils.toastMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber

object RestCall {
    private val client = OkHttpClient()

    suspend fun callFor(url: String): RestResult {
        val restResult = try {
            withContext(Dispatchers.IO) {
                val request = Request.Builder().url(url).build()
                val result = client.newCall(request).execute().body?.string()

                result?.let {
                    RestResult(RestStatus.SUCCESS, result)
                } ?: RestResult(
                    RestStatus.NULL_REST_CALL_BODY,
                    RestCallError.NULL_REST_CALL_BODY.message
                )
            }
        } catch (e: Exception) {
            Timber.e(e)
            RestResult(RestStatus.BAD_REQUEST, RestCallError.BAD_REQUEST.message)
        }
        restResult.isSuccess()
        restResult.message
        return restResult
    }

    enum class RestStatus {
        SUCCESS, NULL_REST_CALL_BODY, BAD_REQUEST
    }

    private suspend fun getMessageFor(success: Boolean, message: String) {
        if (success) Timber.d("REST call succeed!")
        else {
            Timber.e(message)
            toastMessage(message)
        }
    }
}

data class RestResult(
    val status: RestCall.RestStatus,
    val message: String
) {
    fun isSuccess() = status == RestCall.RestStatus.SUCCESS
}

/**
 * This is made an enum class and not a "const val" in order to provide a more general implementation
 * of the method RestCall.callFor()
 */
enum class HttpUrls(val string: String) {
    MAIN_DATA("https://cityme-services.prepro.site/app_dev.php/api/districts/2"),
}

enum class RestCallError(val message: String) {
    NULL_REST_CALL_BODY("Null call request body"),
    BAD_REQUEST("Bad request, check connection or request URL")
}