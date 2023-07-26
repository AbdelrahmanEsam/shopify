package com.example.shopify.checkout.domain.usecase.order

import com.example.shopify.checkout.domain.repository.CartAndCheckoutRepository
import com.example.shopify.orders.data.dto.post.PostOrder
import com.example.shopify.orders.data.dto.post.PostOrderResponse
import com.example.shopify.orders.domain.repository.OrdersRepository
import com.example.shopify.utils.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(private val cartAndCheckoutRepository: CartAndCheckoutRepository){
    suspend fun execute(postOrder: PostOrder): Flow<Response<PostOrderResponse>> {
        return cartAndCheckoutRepository.createOrder(postOrder)
    }
}