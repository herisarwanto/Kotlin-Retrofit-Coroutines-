package com.retrofitcoroutineskt.example1

import retrofit2.Call
import retrofit2.http.GET

interface PostServices {

    @GET("posts")
    fun getPost(): Call<List<PostModel>>


}