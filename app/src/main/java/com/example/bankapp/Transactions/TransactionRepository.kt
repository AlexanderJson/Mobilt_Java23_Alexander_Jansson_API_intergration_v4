package com.example.bankapp.Transactions

import com.example.bankapp.Model.Transaction
import com.example.bankapp.View.TransactionsResponse
import retrofit2.Call

class TransactionRepository (private val apiRequests: TransactionApiRequests) {


        suspend fun getTransaction(token: String): List<Transaction> {
            return apiRequests.getTransaction(token)
        }

        suspend fun addTransaction(token: String, newTransaction: Transaction): TransactionsResponse {
            return apiRequests.addTransaction(token, newTransaction)
        }
    }
