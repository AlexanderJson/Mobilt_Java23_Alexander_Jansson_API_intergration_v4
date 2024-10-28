package com.example.bankapp.View.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp.Transactions.TransactionService
import com.example.bankapp.Transactions.TransactionViewModel


/* eftersom viewmodelProvider endast tar emot "no argument" viewmodels, så
kan man skapa en factory som returnerar viewmodeln med argument
 */
class TransactionViewModelFactory(private val transactionService: TransactionService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
        return TransactionViewModel(transactionService) as T
        
    } 
        throw IllegalArgumentException("Bad viewmodelclass") 
    }
}

// TODO: gör interface av detta!!!