package com.example.bankapp.Transactions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.Transaction
import com.example.bankapp.R
import com.example.bankapp.SharedPreferencesUtil
import com.example.bankapp.SharedPreferencesUtil.getJwtToken
import com.example.bankapp.View.TransactionsResponse
import retrofit2.Call
import retrofit2.Response

class TransactionService(private val apiClient: TransactionApiRequests, private val context: Context) {

    private val transactionRepository = TransactionRepository(apiClient)


    suspend fun addTransaction(context: Context, transaction: Transaction) {

        // hämtar jwt token från shared prefs
        val token = getJwtToken(context)

        if (token != null) {
            val authHeader = "Bearer $token"

            try {
                val response = transactionRepository.addTransaction(authHeader, transaction)
                Log.d("TransactionService ADD ", "Transaction added successfully: ${response}")
            } catch (e: Exception) {
                Log.d("TransactionService", "Transaction added UNSUCCESSFULLY")
            }

        }
    }

    suspend fun getTransaction(): List<Transaction>? {
        val token = SharedPreferencesUtil.getJwtToken(context)
        val authHeader = "Bearer $token"
        return try {
            val transaction = transactionRepository.getTransaction(authHeader)
            Log.d("TransactionService GET", "Transactions recieved: ${transaction}")
            transaction
        }
        catch (e: Exception){
            Log.d("TransactionService GET", "No transactions, buy more")
            null
        }
    }
}
