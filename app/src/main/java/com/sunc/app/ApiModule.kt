package com.sunc.app

import android.content.Context
import android.util.Log
import com.sunc.api.CookiesInterceptor
import com.sunc.api.FamilyApi
import com.sunc.api.SetCookiesInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.File
import javax.inject.Singleton

/**
 * Created by wing on 16-11-23.
 */
@Module(includes = arrayOf(AppModule::class))
class ApiModule {
    @Provides @Singleton fun provideRetrofit(baseUrl: String, client: OkHttpClient) =
            Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

    @Provides fun provideBaseUrl() = "http://172.19.220.164:8080/"
    @Provides fun provideOkhttp(context: Context,interceptor: HttpLoggingInterceptor): OkHttpClient {
        val cacheSize = 1024 * 1024 * 10L
        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, cacheSize)
        val builder = OkHttpClient.Builder()
        builder.cache(cache)
        builder.addInterceptor(CookiesInterceptor())
        builder.addInterceptor(interceptor)
        builder.interceptors().add(SetCookiesInterceptor())
        return builder.build()
    }
    @Provides fun provideInterceptor() :HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor{
            msg -> Log.d("okhttp",msg)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides fun provideApi(retrofit:Retrofit) = retrofit.create(FamilyApi::class.java)
}

