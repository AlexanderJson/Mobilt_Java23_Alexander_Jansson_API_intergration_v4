package com.example.bankapp.Transactions.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Transactions.models.Transaction
import com.example.bankapp.Model.TransactionAdapter
import com.example.bankapp.R
import com.example.bankapp.API.security.SharedPreferencesUtil
import com.example.bankapp.Transactions.fragment.AddTransactionFragment
import com.example.bankapp.Transactions.service.TransactionService
import com.example.bankapp.Transactions.viewmodel.TransactionViewModel
import com.example.bankapp.Users.Authorization.AuthActivity
import com.example.bankapp.Users.activity.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserEconomyActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var transactionService: TransactionService
    private var transactionList = mutableListOf<Transaction>()
    private lateinit var transactionViewModel: TransactionViewModel

    lateinit var addDialog: AddTransactionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_economy)


        // *** MENU *** ///

        // TODO: byta ut med bättre
        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_home -> {
                    true
                }
                R.id.nav_edit_users -> {
                    val intent = Intent(this, ProfileActivity::class.java)
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
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
}
