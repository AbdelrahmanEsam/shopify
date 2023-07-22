package com.example.shopify.checkout.data.dto

data class LineItem(
    val admin_graphql_api_id: String,
    val applied_discount: Any,
    val custom: Boolean,
    val fulfillment_service: String,
    val gift_card: Boolean,
    val grams: Int,
    val id: Long,
    val name: String,
    val price: String,
    val product_id: Long,
    val properties: List<Property>,
    var quantity: Int,
    val requires_shipping: Boolean,
    val sku: String,
    val tax_lines: List<TaxLineX>,
    val taxable: Boolean,
    val title: String,
    val variant_id: Long,
    val variant_title: String,
    val vendor: String
)