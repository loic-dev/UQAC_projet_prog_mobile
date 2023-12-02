package com.example.projet_prog_mobile.di

import android.content.Context
import android.util.Log
import com.example.projet_prog_mobile.R
import com.example.projet_prog_mobile.data.api.AuthInterceptor
import com.example.projet_prog_mobile.data.api.product.ProductApi
import com.example.projet_prog_mobile.data.api.user.UserApi
import com.example.projet_prog_mobile.data.local.user.UserLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
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
    fun provideAuthInterceptor(
        userLocalDataSource: UserLocalDataSource,
        context: Context
    ): Interceptor {
        return AuthInterceptor(userLocalDataSource,context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor { chain ->
                val request: Request = chain.request()
                Log.d("Retrofit", "Sending request " + request.url())
                try {
                    val response: Response = chain.proceed(request)
                    Log.d("Retrofit", "Received response for " + response.request().url())
                    response
                } catch (e: Exception) {
                    Log.e("Retrofit", "Request failed with exception: ${e.message}")
                    throw e
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
        gsonConverterFactory: GsonConverterFactory,
        context: Context
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_url))
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserAPI(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
    @Singleton
    @Provides
    fun provideProductAPI(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)
}