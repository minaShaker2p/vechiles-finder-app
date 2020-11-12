package com.rezkalla.vehiclesfinder.network


import com.google.gson.GsonBuilder
import com.rezkalla.vehiclesfinder.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    private val baseURL: String
    , private val httpClient: OkHttpClient.Builder
    , private val httpLoggingInterceptor: HttpLoggingInterceptor
    , private val builder: Retrofit.Builder
) {

    fun getInstance(): Retrofit {

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("secret-key", BuildConfig.MY_KEY)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        builder.baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

        builder.client(httpClient.build())
        return builder.build()
    }


}