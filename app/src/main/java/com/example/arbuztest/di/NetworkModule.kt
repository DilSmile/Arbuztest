package com.example.arbuztest.di

import com.example.arbuztest.data.remote.ArbuzTestApi
import com.example.arbuztest.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @ArbuzTestUrl
    fun provideArbuzTestUrl(): String{
        return Constant.BASE_URL
    }

    @BasicOkHttpClient
    @Provides
    fun provideBasicOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @AuthenticatedOkHttpClient
    @Provides
    fun provideAuthenticatedOkHttpClient(
        @BasicOkHttpClient okHttpClient: OkHttpClient,
        token: String
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    @ArbuzTestUrl
    @Provides
    @Singleton
    fun getArbuzTestRetrofit(
        @ArbuzTestUrl url: String,
        @BasicOkHttpClient okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getArbuzTestApi(@ArbuzTestUrl retrofit: Retrofit): ArbuzTestApi {
        return retrofit.create(
            ArbuzTestApi::class.java
        )
    }

}

@Qualifier
annotation class ArbuzTestUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BasicOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedOkHttpClient