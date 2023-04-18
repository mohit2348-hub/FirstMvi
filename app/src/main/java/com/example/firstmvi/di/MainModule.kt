package com.example.firstmvi.di

import com.example.firstmvi.data.api.APIService
import com.example.firstmvi.data.repo.GetUserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Singleton
    @Provides
    fun provideApiService(): APIService {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }


    @Provides
    fun provideUserRepo(api: APIService): GetUserRepo {
        return GetUserRepo(api)
    }


}