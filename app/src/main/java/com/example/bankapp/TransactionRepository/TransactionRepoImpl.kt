package com.example.bankapp.TransactionRepository

import com.example.bankapp.Model.Transactions
import com.example.bankapp.Transaction
import com.example.bankapp.View.TransactionsResponse
import javax.inject.Inject

// Denna klass hanterar överföringen av all data från TransactionRepos till Viewmodel/Service
class TransactionRepoImpl @Inject constructor(private val transactionApiRepository: TransactionApiRepository) : TransactionRepo {


    override suspend fun getTransactions(token: String): List<Transaction> {
        return transactionApiRepository.getTransaction(token)
    }

    override suspend fun addTransaction(token: String, transaction: Transaction): TransactionsResponse {
        return transactionApiRepository.addTransaction(token, transaction)
    }

    override suspend fun removeTransaction(token: String, transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(token: String, transaction: Transaction) {
        TODO("Not yet implemented")
    }


}