package com.example.futball.di

import com.example.futball.core.Constants
import com.example.futball.data.api.FutBallApi
import com.example.futball.data.repository.FutBallRepositoryImpl
import com.example.futball.domain.repository.FutBallRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFutBallAPI(): FutBallApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(FutBallApi::class.java)
    }

    @Provides
    fun provideFutBallRepository(futBallApi: FutBallApi): FutBallRepository {
        return FutBallRepositoryImpl(futBallApi)
    }

}