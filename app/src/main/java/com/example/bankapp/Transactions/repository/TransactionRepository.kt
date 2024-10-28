package com.example.bankapp.Transactions.repository

import com.example.bankapp.API.ApiRequests
import com.example.bankapp.Transactions.models.Transaction
import com.example.bankapp.View.TransactionsResponse

class TransactionRepository (private val apiRequests: ApiRequests) {


        suspend fun getTransaction(token: String): List<Transaction> {
            return apiRequests.getTransaction(token)
        }

        suspend fun addTransaction(token: String, newTransaction: Transaction): TransactionsResponse {
            return apiRequests.addTransaction(token, newTransaction)
        }
    }
