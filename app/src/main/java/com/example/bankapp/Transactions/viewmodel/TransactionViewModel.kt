package com.example.bankapp.Transactions.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.Transactions.models.Transaction
import com.example.bankapp.Transactions.service.TransactionService
import kotlinx.coroutines.launch

class TransactionViewModel(private val transactionService: TransactionService) : ViewModel() {


    private val transactionLiveData = MutableLiveData<List<Transaction>?>()
    val transactions: MutableLiveData<List<Transaction>?> get() = transactionLiveData

    fun getTransactions(context: Context){
        viewModelScope.launch {
            val fetchedTransactions = transactionService.getTransaction()
            transactionLiveData.value = fetchedTransactions

        }
    }

}