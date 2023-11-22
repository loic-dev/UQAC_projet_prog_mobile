package com.example.projet_prog_mobile.di

import android.util.Log
import com.example.projet_prog_mobile.data.api.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request = chain.request()
                Log.d("Retrofit", "Sending request " + request.url())

                try {
                    val response: Response = chain.proceed(request)
                    Log.d("Retrofit", "Received response for " + response.request().url())
                    response
                } catch (e: Exception) {
                    // Gérez l'exception ici
                    Log.e("Retrofit", "Request failed with exception: ${e.message}")
                    throw e // Propagez l'exception après la gestion
                }
            }
            .build()
    }
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.111:8000/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Singleton
    @Provides
    fun provideUserAPI(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
}