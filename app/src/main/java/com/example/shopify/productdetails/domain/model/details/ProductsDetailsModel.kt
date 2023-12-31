package com.example.shopify.productdetails.domain.model.details


data class ProductsDetailsModel(
    val id: Long,
    val title: String,
    var currency: String="EGP",
    val vendor: String,
    val description: String,
    val rating: Float,
    val image: ImageModel,
    val images: List<ImageModel>,
    val productType: String,
    val handle: String,
    val status: String,
    val tags: String,
    val options: List<OptionModel>?,
    val variants: List<VariantModel>?,
)
