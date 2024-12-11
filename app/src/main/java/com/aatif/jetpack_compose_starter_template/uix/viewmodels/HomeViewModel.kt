package com.aatif.jetpack_compose_starter_template.uix.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aatif.jetpack_compose_starter_template.Utils.NetworkResult
import com.aatif.jetpack_compose_starter_template.data.remote.models.ItemResponse
import com.aatif.jetpack_compose_starter_template.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ExampleRepository) : ViewModel() {

    private val _items = MutableStateFlow<NetworkResult<List<ItemResponse>>>(NetworkResult.Loading)
    val items: StateFlow<NetworkResult<List<ItemResponse>>> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _items.value = repository.getItems()
        }
    }
}