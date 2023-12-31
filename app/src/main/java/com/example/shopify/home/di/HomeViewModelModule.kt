package com.example.shopify.home.di

import com.example.shopify.home.data.local.HomeLocalDataSource
import com.example.shopify.home.data.local.HomeLocalDataSourceImpl
import com.example.shopify.home.data.remote.brands.BrandsApiClient
import com.example.shopify.home.data.remote.brands.BrandsRemoteSource
import com.example.shopify.home.data.remote.products.ProductRemoteSource
import com.example.shopify.home.data.remote.products.ProductsApiClient
import com.example.shopify.home.data.repository.HomeRepositoryImp
import com.example.shopify.home.domain.repository.HomeRepository
import com.example.shopify.home.domain.usecase.FilterByPriceUseCase
import com.example.shopify.home.domain.usecase.FilterProductsUseCase
import com.example.shopify.home.domain.usecase.GetAllBrandsUseCase
import com.example.shopify.home.domain.usecase.GetAllProductsUseCase
import com.example.shopify.home.domain.usecase.GetProductsByBrandUseCase
import com.example.shopify.home.domain.usecase.discount.GetDiscountCodesUseCase
import com.example.shopify.home.domain.usecase.discount.InsertDiscountCodesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsBrandsRepository(homeRepositoryImp: HomeRepositoryImp): HomeRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsBrandsApiClient(brandsApiClient: BrandsApiClient): BrandsRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindsProductsApiClient(productsApiClient: ProductsApiClient): ProductRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindsHomeLocalDataSource(homeLocalDataSourceImpl: HomeLocalDataSourceImpl) : HomeLocalDataSource

    companion object {
        @Provides
        @ViewModelScoped
        fun provideGetAllBrandsUseCase(repository: HomeRepository): GetAllBrandsUseCase =
            GetAllBrandsUseCase(repository)

        @Provides
        @ViewModelScoped
        fun provideGetAllProductsUseCase(repository: HomeRepository): GetAllProductsUseCase =
            GetAllProductsUseCase(repository)

        @Provides
        @ViewModelScoped
        fun provideGetProductsByBrandUseCase(repository: HomeRepository): GetProductsByBrandUseCase =
            GetProductsByBrandUseCase(repository)

        @Provides
        @ViewModelScoped
        fun provideFilterProductsUseCase(repository: HomeRepository): FilterProductsUseCase =
            FilterProductsUseCase(repository)

        @Provides
        @ViewModelScoped
        fun provideFilterByPriceUseCase(): FilterByPriceUseCase =
            FilterByPriceUseCase()

        @Provides
        @ViewModelScoped
        fun providesGetDiscountCodesUseCase(repository: HomeRepository) : GetDiscountCodesUseCase = GetDiscountCodesUseCase(repository)

        @Provides
        @ViewModelScoped
        fun providesInsertDiscountCodesUseCase(repository: HomeRepository) : InsertDiscountCodesUseCase = InsertDiscountCodesUseCase(repository)
    }
}