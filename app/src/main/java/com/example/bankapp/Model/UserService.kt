package com.example.bankapp.Model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

public interface UserService {

    @GET("/users/get/")
    fun getUsers(
        @Header("Authorization") token: String,
        ):Call<List<User>>


    @POST("/users/register")
    fun registerUser(@Body user: User): Call<Void>


    @POST("/authenticate")
    fun authenticateUser(@Body user: User): Call<Map<String, String>>
}