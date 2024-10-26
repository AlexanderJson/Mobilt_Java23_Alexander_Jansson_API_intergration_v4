package com.example.bankapp.adapters

import com.example.bankapp.Model.Transactions
import com.example.bankapp.View.TransactionsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionRepository {




    @POST("transactions/add")
    suspend fun addTransaction(
       @Header ("Authorization") token: String, //JWT token , user details
       @Body transaction: Transactions // transaction details to add
    ): TransactionsResponse



    @GET("transactions/user")
    suspend fun getTransaction(
        @Header("Authorization") token: String,
    ): List<Transactions>
}