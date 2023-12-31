package com.example.shopify.checkout.data.dto.post

import com.example.shopify.data.dto.PropertiesItem

data class LineItem(
    val quantity: Int,
    val variant_id: Long,
    val properties: List<PropertiesItem>
)