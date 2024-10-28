package com.example.bankapp.Transactions

import com.example.bankapp.Model.Transactions
import com.example.bankapp.View.TransactionsResponse
import retrofit2.Call

class TransactionRepository (private val apiRequests: TransactionApiRequests) {


        fun getTransaction(token: String): Call<List<Transactions>> {
            return apiRequests.getTransaction(token)
        }

        fun addTransaction(token: String, newTransaction: Transactions): Call<TransactionsResponse> {
            return apiRequests.addTransaction(token, newTransaction)
        }
    }
