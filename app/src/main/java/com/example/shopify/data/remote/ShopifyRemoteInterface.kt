package com.example.shopify.data.remote

import com.example.shopify.auth.data.dto.CustomerResponse
import com.example.shopify.checkout.data.dto.DraftOrderById
import com.example.shopify.checkout.data.dto.discountcode.DiscountCodeResponse
import com.example.shopify.checkout.data.dto.post.PostOrder
import com.example.shopify.checkout.data.dto.pricerule.PriceRules
import com.example.shopify.checkout.data.dto.product.OneProductResponse
import com.example.shopify.checkout.data.dto.response.PostResponse
import com.example.shopify.data.dto.DraftOrderResponse
import com.example.shopify.data.dto.Users
import com.example.shopify.data.dto.codes.DiscountCodesResponse
import com.example.shopify.home.data.dto.BrandsResponse
import com.example.shopify.home.data.dto.ProductsResponse
import com.example.shopify.orders.data.dto.OrdersResponse
import com.example.shopify.productdetails.data.dto.draftorder.DraftOrderRequest
import com.example.shopify.productdetails.data.dto.productdetails.ProductDetailsResponse
import com.example.shopify.search.data.dto.SearchProductsResponse
import com.example.shopify.settings.data.dto.address.AddressResponse
import com.example.shopify.settings.data.dto.address.SendAddressDTO
import com.example.shopify.settings.data.dto.currencies.CurrenciesResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopifyRemoteInterface {

    @GET("currencies.json")
    suspend fun getAllCurrencies(): CurrenciesResponse

    @GET("smart_collections.json")
    suspend fun getAllBrands(): BrandsResponse

    @GET("products.json")
    suspend fun getAllProducts(): ProductsResponse

    @GET("products.json")
    suspend fun getProductsByBrand(@Query("vendor") vendor: String): ProductsResponse

    @GET("products.json")
    suspend fun filterProducts(
        @Query("collection_id") collectionId: Long?,
        @Query("product_type") productType: String = ""
    ): ProductsResponse


    @GET("products/{productId}.json")
    suspend fun getProductById(@Path("productId") productId : String): OneProductResponse




    @POST("customers.json")
    suspend fun createCustomer(@Body customerResponse: CustomerResponse): CustomerResponse

    @GET("draft_orders.json")
    suspend fun getDraftOrders(
        @Query("customer_id") id: String = "",
        @Query("note") note: String = ""
    ): DraftOrderResponse


    @GET("draft_orders/{draftOrderId}.json")
    suspend fun getDraftOrderById(@Path("draftOrderId") draftOrderId:String): DraftOrderById

    @DELETE("draft_orders/{draftOrderId}.json")
    suspend fun removeDraftOrder(@Path("draftOrderId") draftOrderId:String)


    @PUT("draft_orders/{draftOrderId}.json")
    suspend fun updateDraftOrder(@Path("draftOrderId") draftOrderId:String ,@Body draftOrder: DraftOrderById)

    
    @GET("orders.json")
    suspend fun getCustomerOrders(@Query("email") customerEmail: String): OrdersResponse

    @GET("price_rules/1396109508887/discount_codes.json")
    suspend fun getDiscountCodes() : DiscountCodesResponse?

    @PUT("price_rules/1396109508887/discount_codes/{id}.json")
    suspend fun updateDiscountCode(@Path("id")id : String)


    @GET("price_rules/1396109508887/discount_codes/{id}.json")
    suspend fun getDiscountCodeById(@Path("id")id : String) : DiscountCodeResponse?

    @GET("products/{productId}.json")
    suspend fun getProductDetailsById(@Path("productId") productId : String): ProductDetailsResponse


    @GET("draft_orders.json")
    suspend fun getDraftOrders(
        @Query("fields") fields: String = "",
    ): DraftOrderResponse

    @POST("draft_orders.json")
    suspend fun createDraftOrder(@Body draftOrderRequest: DraftOrderRequest):DraftOrderRequest

    @GET("price_rules/{id}.json")
    suspend fun getPriceRule(@Path("id") id : String) : PriceRules

    @DELETE("draft_orders/{id}.json")
    suspend fun deleteDraftOrder(@Path("id")id : String)

    @POST("orders.json")
    suspend fun createOrder(@Body postOrder: PostOrder): PostResponse


    @GET("customers.json")
    suspend fun getUserWithEmail(@Query(value = "email") email:String):Users

    @POST("/admin/api/2023-07/customers/{customer_id}/addresses.json")
    suspend fun createAddressForCustomer(@Path("customer_id") customerId: String, @Body customerAddress: SendAddressDTO): AddressResponse

    @GET("/admin/api/2023-07/customers/{customer_id}/addresses.json")
    suspend fun getAddressesForCustomer(@Path("customer_id") customerId: String): AddressResponse

    @PUT("/admin/api/2023-07/customers/{customer_id}/addresses/{address_id}/default.json")
    suspend fun makeAddressDefault(@Path("customer_id") customerId: String, @Path("address_id") addressId: String): AddressResponse
    @DELETE("/admin/api/2023-07/customers/{customer_id}/addresses/{address_id}.json")
    suspend fun deleteAddressForCustomer(@Path("customer_id") customerId: String, @Path("address_id") addressId: String)

    @GET("products.json")
    suspend fun searchProducts(
        @Query("fields") fields: String = "id,image,title"
    ): SearchProductsResponse
}