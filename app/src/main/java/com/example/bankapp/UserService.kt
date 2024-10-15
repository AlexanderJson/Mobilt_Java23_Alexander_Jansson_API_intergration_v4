package com.example.bankapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

public interface UserService {
    @GET("/users/get/")

    fun getUsers():Call<List<User>>


    @POST("/users/register")
    fun registerUser(@Body user: User): Call<Void>
}