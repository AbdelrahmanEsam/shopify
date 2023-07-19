package com.example.shopify.di

import android.content.Context
import com.example.shopify.BuildConfig
import com.example.shopify.utils.connectivity.ConnectivityObserver
import com.example.shopify.utils.connectivity.NetworkConnectivityObserver
import com.example.shopify.data.datastore.DataStoreUserPreferences
import com.example.shopify.data.datastore.DataStoreUserPreferencesImpl
import com.example.shopify.data.remote.AuthorizationInterceptor
import com.example.shopify.data.remote.remoteInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{

    @Singleton
    @Provides
    fun providesConnectivityObserver(@ApplicationContext context: Context) : ConnectivityObserver
    = NetworkConnectivityObserver(context = context)


    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStoreUserPreferences {
        return  DataStoreUserPreferencesImpl(applicationContext)
    }

    @Singleton
    @Provides
    fun providesAuthorizationInterceptor()  = AuthorizationInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(authorizationInterceptor: AuthorizationInterceptor) : OkHttpClient

    {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()
    }



    @Singleton
    @Provides
    fun providesRetrofitApi(okHttpClient: OkHttpClient): remoteInterface
            = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create()).build()
        .create(remoteInterface::class.java)

}