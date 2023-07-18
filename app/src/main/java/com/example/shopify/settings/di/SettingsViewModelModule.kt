package com.example.shopify.settings.di

import com.example.shopify.data.datastore.DataStoreUserPreferences
import com.example.shopify.settings.data.local.LocalDataSource
import com.example.shopify.settings.data.local.LocalDataSourceImpl
import com.example.shopify.settings.data.repository.SettingsRepositoryImpl
import com.example.shopify.settings.domain.repository.SettingsRepository
import com.example.shopify.settings.domain.usecase.ReadStringFromDataStoreUseCase
import com.example.shopify.settings.domain.usecase.SaveStringToDataStoreUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsLocalDataSource(localDataStoreImpl: LocalDataSourceImpl) : LocalDataSource



    @Binds
    @ViewModelScoped
   abstract fun bindsSettingsRepository(repositoryImpl: SettingsRepositoryImpl) : SettingsRepository


    companion object{

        @Provides
        @ViewModelScoped
        fun providesReadStringFromDataStoreUseCase(repository: SettingsRepository) : ReadStringFromDataStoreUseCase
        {
            return ReadStringFromDataStoreUseCase(repository)
        }


        @Provides
        @ViewModelScoped
        fun providesSaveStringToDataStoreUseCase(repository: SettingsRepository) : SaveStringToDataStoreUseCase
                = SaveStringToDataStoreUseCase(repository)
    }
}