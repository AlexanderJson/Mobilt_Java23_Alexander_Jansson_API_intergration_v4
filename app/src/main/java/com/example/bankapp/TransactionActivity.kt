package com.example.bankapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.activity.viewModels
import com.example.bankapp.TransactionRepository.TransactionRepo

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity() {

    @Inject lateinit var transactionRepo: TransactionRepo

    private val viewModel : TransactionViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
    }

}