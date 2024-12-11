package com.aatif.jetpack_compose_starter_template.data.remote

import com.aatif.jetpack_compose_starter_template.data.remote.models.ItemResponse
import com.aatif.jetpack_compose_starter_template.data.remote.models.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Example: Fetch a list of items
    @GET("items")
    suspend fun getItems(): Response<List<ItemResponse>>
    @POST("auth/refresh")
    suspend fun refreshToken(@Body body: Map<String, String?>): Response<RefreshTokenResponse>


}