package com.rezkalla.vehiclesfinder.di


import com.rezkalla.remote.api.VehiclesApiService
import com.rezkalla.vehiclesfinder.network.RetrofitClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


private const val BASE_URL = "https://api.jsonbin.io/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideHttpClientBuilder() = OkHttpClient().newBuilder()

    @Provides
    @Singleton
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @Named("BASE_URL") baseURL: String,
        httpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return RetrofitClient(
            baseURL,
            httpClientBuilder,
            httpLoggingInterceptor,
            retrofitBuilder
        ).getInstance()
    }

    @Provides
    @Singleton
    fun provideVehiclesApiService(retrofit: Retrofit): VehiclesApiService =
        retrofit.create(VehiclesApiService::class.java)
}