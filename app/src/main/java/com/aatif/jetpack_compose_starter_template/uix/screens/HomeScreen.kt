package com.aatif.jetpack_compose_starter_template.uix.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aatif.jetpack_compose_starter_template.Utils.NetworkResult
import com.aatif.jetpack_compose_starter_template.uix.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.items.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is NetworkResult.Loading -> Text(text = "Loading...")
            is NetworkResult.Success -> Text(text = "Hello ${(uiState as NetworkResult.Success<String>).data }"  )
            is NetworkResult.Error -> Text(text = "Error: ${(uiState as NetworkResult.Error).message}")
        }
    }
}