package com.example.shopify.checkout.domain.repository

import com.example.shopify.checkout.data.dto.post.PostOrder
import com.example.shopify.checkout.data.dto.response.PostResponse
import com.example.shopify.data.dto.codes.DiscountCode
import com.example.shopify.utils.response.Response
import kotlinx.coroutines.flow.Flow

interface CartAndCheckoutRepository {

    suspend fun <T> getCartItems() : Flow<Response<T>>

    suspend fun <T> deleteItemFromCart(id : String) : Flow<Response<T>>

    suspend fun <T> updateItemFromCart(id : String,quantity : String) : Flow<Response<T>>

    suspend fun <T> deleteDiscountCodeFromDatabase(code : DiscountCode) : Flow<Response<T>>


    suspend fun <T> getDiscountCodeById(id : String) : Flow<Response<T>>

    suspend fun <T> getAllCustomerAddress(customerId : String) : Flow<Response<T>>

    suspend fun <T> getAllDiscountCodes() : Flow<Response<T>>


    suspend fun <T> getUserEmail() : Flow<Response<T>>

    suspend fun<T> getUserPhone() : Flow<Response<T>>

    suspend fun <T> getPriceRule(id :String): Flow<Response<T>>


    suspend fun  createOrder(postOrder: PostOrder): Flow<Response<PostResponse>>



    suspend fun <T> deleteDraftOrder(id : String) : Flow<Response<T>>

    suspend fun <T> getCustomerId()  : Flow<Response<T>>


    suspend fun <T> exchangeCurrency(from : String , to : String)  : Flow<Response<T>>

}