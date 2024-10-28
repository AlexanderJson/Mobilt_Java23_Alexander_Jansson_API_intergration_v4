package com.example.bankapp.Transactions.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.API.ApiClient
import com.example.bankapp.Transactions.models.Transaction
import com.example.bankapp.Model.TransactionAdapter
import com.example.bankapp.R
import com.example.bankapp.Users.Authorization.AuthActivity
import com.example.bankapp.API.security.SharedPreferencesUtil
import com.example.bankapp.Transactions.fragment.AddTransactionFragment
import com.example.bankapp.Transactions.service.TransactionService
import com.example.bankapp.Transactions.viewmodel.TransactionViewModel
import com.example.bankapp.Transactions.viewmodel.TransactionViewModelFactory
import com.example.bankapp.Users.activity.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

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

        // *** Instansierar  *** ///
        transactionService = TransactionService(ApiClient.transactionApi, this)
        val factory = TransactionViewModelFactory(transactionService)
        transactionViewModel = ViewModelProvider(this,factory)[TransactionViewModel::class.java]
        addDialog = AddTransactionFragment()


        // *** Hämta live data till recycler view *** ///
        transactionViewModel.getTransactions(this)
        transactionViewModel.transactions.observe(this) { transactions ->
            transactionList.clear()
            transactions?.let { transactionList.addAll(it) }
            transactionAdapter.notifyDataSetChanged()
        }

        // *** Recycler View *** ///
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
                    // återanvänder aktivitet och flyttar upp i stack om den är i bacstack
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                    startActivity(intent)

                    true
                }

                R.id.nav_user_economy -> {
                    val intent = Intent(this, UserEconomyActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT

                    startActivity(intent)

                    true
                }

                R.id.nav_logout -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    // rensar alla stacks och skapar ny för authActivity
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    // rensar shared prefs så ingen tidigsre info är med
                    SharedPreferencesUtil.cleanPreferences(this)
                    startActivity(intent)
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


