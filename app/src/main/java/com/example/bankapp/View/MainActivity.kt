package com.example.bankapp.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.Transaction
import com.example.bankapp.Model.TransactionAdapter
import com.example.bankapp.R
import com.example.bankapp.SharedPreferencesUtil
import com.example.bankapp.Transactions.TransactionRepository
import com.example.bankapp.Transactions.TransactionService
import com.example.bankapp.Transactions.TransactionViewModel
import com.example.bankapp.View.ui.login.TransactionViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), AddTransactionFragment.OnTransactionAddedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var transactionService: TransactionService
    private var transactionList = mutableListOf<Transaction>()
    private lateinit var transactionViewModel: TransactionViewModel

    lateinit var addDialog: AddTransactionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        // *** Recycler View + LiveData *** ///

        transactionService = TransactionService(ApiClient.transactionApi, this)

        val factory = TransactionViewModelFactory(transactionService)
        transactionViewModel = ViewModelProvider(this,factory)[TransactionViewModel::class.java]

        addDialog = AddTransactionFragment()


        // *** Starts service functions *** ///

        transactionViewModel.getTransactions(this)

        transactionViewModel.transactions.observe(this) { transactions ->
            transactionList.clear()
            transactions?.let { transactionList.addAll(it) }
            transactionAdapter.notifyDataSetChanged()
        }


        recyclerView = findViewById(R.id.transactionsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        transactionAdapter = TransactionAdapter(transactionList)
        recyclerView.adapter = transactionAdapter



        // *** UI ELEMENT *** ///
        val addBtn = findViewById<android.widget.Button>(R.id.addExpenseButton)

        addBtn.setOnClickListener {
            addDialog.show(supportFragmentManager, "AddTransactionFragment")
        }

        // ta in textEdit - kör remove metod
        val removeBtn = findViewById<android.widget.Button>(R.id.removeExpenseButton)



        // *** MENU *** ///

        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    true
                }

                R.id.nav_edit_users -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.nav_logout -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                  //  userService.logoutUser()
                    finish()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


//    // gör universiell ?
//    private fun navigateFragment(fragment: Fragment){
//        val navigation = supportFragmentManager.beginTransaction()
//        // tar fragment som argument
//        navigation.replace(R.id.fragment_container, fragment)
//        // behåller i backstack
//        navigation.addToBackStack(null)
//        navigation.commit()
//    }



    // hämtar värdena returnerade från fragment
    override  fun onTransactionAdded(transaction: Transaction){
        lifecycleScope.launch {
            transactionService.addTransaction(this@MainActivity,transaction)
            Log.d("MainActivity", "Transaction added: $transaction.amount")
            transactionList.add(transaction)
            transactionAdapter.notifyItemInserted(transactionList.size - 1)
            recyclerView.scrollToPosition(transactionList.size - 1)
        }
        }
    }


