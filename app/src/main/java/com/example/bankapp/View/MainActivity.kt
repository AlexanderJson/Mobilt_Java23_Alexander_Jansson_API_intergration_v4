package com.example.bankapp.View

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.Transactions
import com.example.bankapp.R
import com.example.bankapp.Model.User
import com.example.bankapp.Model.UserAdapter
import com.example.bankapp.Model.UserService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), AddTransactionFragment.OnTransactionAddedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val addBtn = findViewById<android.widget.Button>(R.id.addExpenseButton)
        val dialog = AddTransactionFragment()


        addBtn.setOnClickListener {
            dialog.show(supportFragmentManager, "AddTransactionDialog")
        }


        val transactionList = listOf(
            Transactions(1, 1, 68.25, "Nöje", "Espresso House", "2024-10-12", "expense", false, "2024-10-01"),
            Transactions(2, 1, 578.35, "Mat/Livsmedel", "ICA Malmborgs", "2024-10-12", "expense", false, "2024-10-01")
        )

    }
    override fun onTransactionAdded(amount: Double){
        Log.d("MainActivity", "Transaction added: $amount")
    }
}
