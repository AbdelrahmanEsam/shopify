package com.example.shopify.checkout.data.remote

import com.example.shopify.checkout.data.dto.post.PostOrder
import com.example.shopify.data.dto.codes.DiscountCode
import com.example.shopify.home.domain.model.discountcode.DiscountCodeModel
import com.example.shopify.utils.response.Response
import kotlinx.coroutines.flow.Flow

interface CartAndCheckOutRemoteDataSource {

    suspend fun <T> getCartItems() : Flow<Response<T>>

    suspend fun <T> getProductById(id : String) : Flow<Response<T>>

    suspend fun <T> deleteItemFromCart(id : String) : Flow<Response<T>>


    suspend fun <T> updateItemFromCart(id : String,quantity : String) : Flow<Response<T>>




    suspend fun <T> getDiscountCodeById(id : String) : Flow<Response<T>>


    suspend fun <T> getUserEmail() : Flow<Response<T>>

    suspend  fun<T> getUserPhone() : Flow<Response<T>>


    suspend fun <T> getPriceRule(id :String): Flow<Response<T>>

    suspend fun <T> getAllCustomerAddress(customerId : String) : Flow<Response<T>>
    suspend fun <T> deleteDraftOrder(orderId : String) : Flow<Response<T>>



    suspend fun <T> createOrder(postOrder: PostOrder): Flow<Response<T>>


    suspend fun <T> getCustomerId()  : Flow<Response<T>>


    suspend fun <T> exchangeCurrency(from : String , to : String)  : Flow<Response<T>>




}