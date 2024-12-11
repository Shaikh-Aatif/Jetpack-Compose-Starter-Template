package com.aatif.jetpack_compose_starter_template.data.repository

import com.aatif.jetpack_compose_starter_template.Utils.NetworkResult
import com.aatif.jetpack_compose_starter_template.data.remote.ApiService
import com.aatif.jetpack_compose_starter_template.data.remote.models.ItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExampleRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getItems(): NetworkResult<List<ItemResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getItems()
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body() ?: emptyList())
                } else {
                    NetworkResult.Error(response.message())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}