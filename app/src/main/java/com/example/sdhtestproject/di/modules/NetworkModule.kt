package com.example.sdhtestproject.di.modules

import com.example.sdhtestproject.network.RetrofitServices
import com.example.sdhtestproject.repositotys.PillsRepository
import com.example.sdhtestproject.utils.RetrofitUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideInterceptor() : HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) : OkHttpClient{
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit: Retrofit.Builder = Retrofit.Builder()
        retrofit.baseUrl(RetrofitUtils.BASE_URL)
        retrofit.addConverterFactory(GsonConverterFactory.create())
        retrofit.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofit.client(okHttpClient)
        return retrofit.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitRepository(retrofit: Retrofit): PillsRepository {
        return PillsRepository(retrofit.create(RetrofitServices::class.java)
        )
    }


}