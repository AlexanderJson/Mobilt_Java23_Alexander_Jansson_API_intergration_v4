package com.example.bankapp.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankapp.Model.Transactions
import com.example.bankapp.adapters.TransactionRepository

class TransactionsViewmodel : ViewModel(){

    private val transactionData = MutableLiveData<List<Transactions>>()
    val transactions: LiveData<List<Transactions>> get() = transactionData

   // private val repository: TransactionRepository = TransactionRepository()

}