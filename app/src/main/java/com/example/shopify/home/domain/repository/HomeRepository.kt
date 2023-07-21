package com.example.shopify.home.domain.repository

import com.example.shopify.utils.response.Response
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun <T> getAllBrands(): Flow<Response<T>>
    suspend fun <T> getAllProducts(): Flow<Response<T>>

    suspend fun <T> getProductsByBrand(brand: String): Flow<Response<T>>

    suspend fun <T> filterProducts(category: Long?, productType: String): Flow<Response<T>>
}