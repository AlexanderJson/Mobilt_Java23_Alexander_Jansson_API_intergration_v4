package com.example.bankapp.Model

import com.example.bankapp.Transactions.TransactionApiRequests
import com.example.bankapp.View.TransactionsResponse
import retrofit2.Call

class UserRepository (private val transactionApi: TransactionApiRequests) {


    fun getUser(token: String): Call<List<User>> {
        return transactionApi.getUsers(token)
    }

    fun registerUser(newUser: User): Call<Void> {
        return transactionApi.registerUser(newUser)
    }

    fun authenticate(newUser: User): Call<Map<String, String>> {
        return transactionApi.authenticateUser(newUser)
    }
}
