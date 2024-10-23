package com.example.bankapp

import android.view.SurfaceControl
import com.example.bankapp.Model.Transactions
import com.example.bankapp.View.TransactionsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionService {




    @POST("transactions/add")
    fun addTransaction(
       @Header ("Authorization") token: String, //JWT token , user details
       @Body transaction: Transactions // transaction details to add
    ): Call<TransactionsResponse>



    @GET("transactions/user")
    fun getTransaction(
        @Header("Authorization") token: String,
    ): Call<List<Transactions>>
}