package com.example.shopify.favorites.presentation

import com.example.shopify.favorites.domain.model.FavoriteProductModel
import com.example.shopify.search.domain.model.SearchProductModel

data class FavoritesState(

    val products: List<FavoriteProductModel>? = emptyList(),
    val loading: Boolean = true,
    val empty: Boolean = true,
    val guest: Boolean = false,
    val searchResult: List<SearchProductModel> = emptyList(),
    val currency: String = "EGP",
    val currencyFactor: Double = 0.0

)
