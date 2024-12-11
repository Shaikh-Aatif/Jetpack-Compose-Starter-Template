package com.aatif.jetpack_compose_starter_template.data.remote.models

data class ItemResponse(
    val id: String,
    val name: String,
    val description: String,
    val price: Double
)