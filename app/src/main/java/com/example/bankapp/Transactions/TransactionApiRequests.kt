package com.example.bankapp.Transactions

import com.example.bankapp.Model.Transaction
import com.example.bankapp.Model.User
import com.example.bankapp.View.TransactionsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionApiRequests {




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
    fun getUsers(
        @Header("Authorization") token: String,
    ):Call<List<User>>


    @POST("/users/register")
    fun registerUser(@Body user: User): Call<Void>


    @POST("/authenticate")
    fun authenticateUser(@Body user: User): Call<Map<String, String>>
}


