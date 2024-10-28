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
import com.example.bankapp.Model.TransactionAdapter
import com.example.bankapp.Model.Transactions
import com.example.bankapp.R
import com.example.bankapp.SharedPreferencesUtil
import com.example.bankapp.Transactions.TransactionRepository
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), AddTransactionFragment.OnTransactionAddedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter

    private var transactionList = mutableListOf<Transactions>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val addBtn = findViewById<android.widget.Button>(R.id.addExpenseButton)
        val dialog = AddTransactionFragment()
        val testBtn = findViewById<android.widget.Button>(R.id.removeExpenseButton)
        getTransaction()

        recyclerView = findViewById(R.id.transactionsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        transactionAdapter = TransactionAdapter(transactionList)

        recyclerView.adapter = transactionAdapter


        addBtn.setOnClickListener {
            dialog.show(supportFragmentManager, "AddTransactionDialog")
        }

        testBtn.setOnClickListener {
            getTransaction()
        }

    }

    private fun getTransaction(){
        val token = SharedPreferencesUtil.getJwtToken(this)
        val authHeader = "Bearer $token"
        val repository = TransactionRepository(ApiClient.transactionApi)

        repository.getTransaction(authHeader).enqueue(object : retrofit2.Callback<List<Transactions>> {
            override fun onResponse(call: Call<List<Transactions>>, response: Response<List<Transactions>>) {
                if (response.isSuccessful) {
                   val transactions = response.body()

                    transactionList.clear()
                    transactions?.let { transactionList.addAll(it) }

                    transactionAdapter.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "Transactions fetched successfully", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Transactions>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
             })

    }


    override fun onTransactionAdded(transaction: Transactions){
        addTransaction(this,transaction)
        Log.d("MainActivity", "Transaction added: $transaction.amount")
        transactionList.add(transaction)
        transactionAdapter.notifyItemInserted(transactionList.size - 1)
        recyclerView.scrollToPosition(transactionList.size - 1)
    }


    private fun addTransaction(context: Context, transaction: Transactions){
        val token = SharedPreferencesUtil.getJwtToken(context);

        if (token != null){
            val transactionRepository = TransactionRepository(ApiClient.transactionApi)
            val authHeader = "Bearer $token"
            Log.d("MainActivity", "Token in addTransaction: $authHeader")

            transactionRepository.addTransaction(authHeader,transaction)
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


