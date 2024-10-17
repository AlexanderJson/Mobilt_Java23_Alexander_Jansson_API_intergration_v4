package com.example.bankapp.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.example.bankapp.SharedPreferencesUtil
import com.example.bankapp.TransactionService
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
        val testBtn = findViewById<android.widget.Button>(R.id.removeExpenseButton)

        testBtn.setOnClickListener {
            val token = SharedPreferencesUtil.getJwtToken(this)
            Log.d("MainActivity", "Token in testBtn click: $token")
        }

        addBtn.setOnClickListener {
            dialog.show(supportFragmentManager, "AddTransactionDialog")
        }


        val transactionList = listOf(
            Transactions(1, 1, 68.25, "Nöje", "Espresso House", "2024-10-12", "expense", false, "2024-10-01"),
            Transactions(2, 1, 578.35, "Mat/Livsmedel", "ICA Malmborgs", "2024-10-12", "expense", false, "2024-10-01")
        )

    }

    override fun onTransactionAdded(transactions: Transactions){
        addTransaction(this,transactions)
        Log.d("MainActivity", "Transaction added: $transactions.amount")
    }


    private fun addTransaction(context: Context, transaction: Transactions){
        val token = SharedPreferencesUtil.getJwtToken(context);

        if (token != null){
            val transactionsService = ApiClient.createService(TransactionService::class.java)
            val authHeader = "Bearer $token"
            Log.d("MainActivity", "Token in addTransaction: $authHeader")

            transactionsService.addTransaction(authHeader,transaction)
                .enqueue(object : retrofit2.Callback<TransactionsResponse> {
                    override fun onResponse(
                        call: Call<TransactionsResponse>,
                        response: Response<TransactionsResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("AddTransaction", "Transaction added successfully")
                            Toast.makeText(
                                context,
                                "Transaction added successfully",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            Log.e("AddTransaction", "Error adding transaction: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<TransactionsResponse>, t: Throwable) {
                        Log.e("AddTransaction", "Error: ${t.message}")
                    }
                })
        }
    }
}


