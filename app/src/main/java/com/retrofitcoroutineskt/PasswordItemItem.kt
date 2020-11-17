package com.retrofitcoroutineskt


import com.google.gson.annotations.SerializedName

data class PasswordItemItem(
    @SerializedName("password")
    val password: String,
    @SerializedName("id_pegawai")
    val id_pegawai: String,
    @SerializedName("new_password")
    val new_password: String

)