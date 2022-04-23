package com.example.pointsofinterest.get_data

import com.example.pointsofinterest.utils.toastMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber

object RestCall {
    private val client = OkHttpClient()

    suspend fun callFor(url: HttpUrls): RestResult {
        val restResult = try {
            withContext(Dispatchers.IO) {
                val request = Request.Builder().url(url.string).build()
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
        return restResult
    }

    enum class RestStatus {
        SUCCESS, NULL_REST_CALL_BODY, BAD_REQUEST
    }
}

data class RestResult(val status: RestCall.RestStatus, val message: String) {

    suspend fun isSuccess(): Boolean {
        return if (status != RestCall.RestStatus.SUCCESS) {
            Timber.e(message)
            withContext(Dispatchers.Main) {
                toastMessage(message)
            }
            false
        } else {
            withContext(Dispatchers.Main) {
                toastMessage("Data cached")
            }
            true
        }
    }
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