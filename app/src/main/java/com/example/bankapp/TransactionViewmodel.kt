package com.example.bankapp

import androidx.lifecycle.ViewModel
import com.example.bankapp.TransactionRepository.TransactionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewmodel @Inject constructor(private val transactionRepo: TransactionRepo)
    : ViewModel()


{



}