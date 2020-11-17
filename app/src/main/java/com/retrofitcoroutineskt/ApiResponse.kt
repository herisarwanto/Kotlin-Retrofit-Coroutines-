package com.retrofitcoroutineskt


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)