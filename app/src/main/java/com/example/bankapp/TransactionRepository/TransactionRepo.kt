package com.example.bankapp.TransactionRepository

import com.example.bankapp.Transaction
import com.example.bankapp.View.TransactionsResponse


// interface med alla crud funktioner (abstrahera mer ? en för alla?)

// Denna interface håller "boilerplate" för hur funktionerna ska funka och vad de ska returnera.
// håller denna sepererad från apiRepository, eftersom det är delvis lättare att felsöka och för att
// det är mer modulärt
interface TransactionRepo {

    suspend fun getTransactions(token: String): List<Transaction>
    suspend fun addTransaction(token: String, transaction: Transaction): TransactionsResponse
    suspend fun removeTransaction(token: String, transaction: Transaction)
    suspend fun updateTransaction(token: String, transaction: Transaction)

}