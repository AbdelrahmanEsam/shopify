package com.example.shopify.home.domain.model

data class OptionModel(
    val id: Long,
    val name: String,
    val position: Int,
    val productId: Long,
    val values: List<String>?
)
