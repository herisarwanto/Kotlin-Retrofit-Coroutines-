package com.retrofitcoroutineskt

import com.retrofitcoroutineskt.example1.PostModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface AlbumServices {

    @GET("/albums")
    suspend fun getAlbum() : Response<Album>

    //With query parameter
    @GET("/albums")
    suspend fun getSortedAlbum(@Query("userId") userId: Int) : Response<Album>

    //request with path parameter
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int) : Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumItem) : Response<AlbumItem>

    //for reset password simak
    @POST("index.php/services/develop/Dev_Password_User/reset_password")
    suspend fun updatePassword1(@Body password: PasswordItemItem) : Response<PasswordItemItem>

    @POST("index.php/services/develop/Dev_Password_User/reset_password")
    suspend fun updatePassword2(@Body password: PasswordItemItem) : Response<ApiResponse>

}