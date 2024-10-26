package com.example.bankapp.TransactionRepository

import com.example.bankapp.Model.Transactions
import com.example.bankapp.Transaction
import com.example.bankapp.View.TransactionsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


// denna klass hanterar all API logik, så behövs endpoints ändras, ny api logik läggas på görs det här.
interface TransactionApiRepository {




    @POST("transactions/add")
    suspend fun addTransaction(
       @Header ("Authorization") token: String, //JWT token , user details
       @Body transaction: Transaction // transaction details to add
    ): TransactionsResponse



    @GET("transactions/user")
    suspend fun getTransaction(
        @Header("Authorization") token: String,
    ): List<Transaction>
}