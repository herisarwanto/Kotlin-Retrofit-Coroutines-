package com.retrofitcoroutineskt

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object {
        //        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val BASE_URL = "http://103.51.44.163/"

        //for handle network
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        const val username = "53rv!ces"
        val password = "53rv!ces;514k!"

        //add BasicAuthInterceptor to OkHttp client
        val client =  OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(username,     password))
            .build()

//        val client = OkHttpClient.Builder().apply {
////            this.addInterceptor(interceptor)
//            this.addInterceptor(BasicAuthInterceptor(username, password))
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .writeTimeout(25, TimeUnit.SECONDS)
//        }.build()

        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}