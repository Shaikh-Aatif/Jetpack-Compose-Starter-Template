package com.aatif.jetpack_compose_starter_template.data
import com.aatif.jetpack_compose_starter_template.Utils.TokenManager
import com.aatif.jetpack_compose_starter_template.data.remote.ApiService
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager,
    private val apiService: ApiService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // Fetch the current access token
        val accessToken = tokenManager.getAccessToken()

        // Build the request with the access token
        val originalRequest = chain.request()
        val authenticatedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        // Proceed with the request
        val response = chain.proceed(authenticatedRequest)

        // If the response indicates an expired token, refresh it
        if (response.code == 401) {
            synchronized(this) {
                val newAccessToken = refreshAccessToken()
                if (newAccessToken != null) {
                    // Save the new access token
                    tokenManager.saveAccessToken(newAccessToken)

                    // Retry the original request with the new token
                    val newRequest: Request = originalRequest.newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization", "Bearer $newAccessToken")
                        .build()

                    return chain.proceed(newRequest)
                }
            }
        }

        return response
    }

    /**
     * Calls the refresh token API to get a new access token.
     */
    private fun refreshAccessToken(): String? {
        val refreshToken = tokenManager.getRefreshToken()
        return try {
            // Make the API call to refresh the token
            val response = runBlocking {
                mapOf("refresh_token" to refreshToken)?.let { apiService.refreshToken(it) }
            }

            // Check if the response is successful and extract the token
            if (response?.isSuccessful == true) {
                val newToken = response.body()?.accessToken
                newToken
            } else {
                // Log error or handle unsuccessful response
                null
            }
        } catch (e: Exception) {
            // Handle exception (e.g., network error)
            e.printStackTrace()
            null
        }
    }

}
