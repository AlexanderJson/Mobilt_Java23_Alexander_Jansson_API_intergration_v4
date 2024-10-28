package com.example.bankapp.API

import com.example.bankapp.Transactions.models.Transaction
import com.example.bankapp.Users.models.User
import com.example.bankapp.Users.repository.UserRepository
import com.example.bankapp.View.TransactionsResponse
import com.example.bankapp.View.UsersResponse

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiRequests {




    @POST("transactions/add")
    suspend fun addTransaction(
       @Header ("Authorization") token: String, //JWT token , user details
       @Body transaction: Transaction // transaction details to add
    ): TransactionsResponse



    @GET("transactions/user")
   suspend  fun getTransaction(
        @Header("Authorization") token: String,
    ): List<Transaction>


    @GET("/users/get/")
    suspend fun getUsers(
        @Header("Authorization") token: String,
    ): List<User>


    @POST("/users/register")
    suspend fun registerUser(@Body user: User): Response<Void>


    @POST("/authenticate")
    suspend fun authenticateUser(@Body user: User): Response<UsersResponse>
}


