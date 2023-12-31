package com.example.shopify.productdetails.domain.model.details

data class OptionModel(
    val id: Long,
    val name: String,
    val position: Int,
    val productId: Long,
    val values: List<String>?
)
