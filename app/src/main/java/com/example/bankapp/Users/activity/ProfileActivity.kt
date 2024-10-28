package com.example.bankapp.Users.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.API.ApiClient
import com.example.bankapp.Users.models.User
import com.example.bankapp.Model.UserAdapter
import com.example.bankapp.Users.repository.UserRepository
import com.example.bankapp.R
import com.example.bankapp.Users.Authorization.AuthActivity
import com.example.bankapp.API.security.SharedPreferencesUtil
import com.example.bankapp.Transactions.activity.UserEconomyActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


//        getUser()

        recyclerView = findViewById(R.id.userRecyclerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList)

        recyclerView.adapter = userAdapter





        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigatonView.selectedItemId = R.id.nav_edit_users

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

//    private fun getUser(){
//
//        val token = SharedPreferencesUtil.getJwtToken(this)
//        val authHeader = "Bearer $token"
//        val repository = UserRepository(ApiClient.transactionApi)
//
//        repository.getUser(authHeader).enqueue(object : retrofit2.Callback<List<User>> {
//            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                if (response.isSuccessful) {
//                    val user = response.body()
//
//                    userList.clear()
//                    user?.let { userList.addAll(it) }
//
//                    userAdapter.notifyDataSetChanged()
//                    Toast.makeText(this@ProfileActivity, "Transactions fetched successfully", Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                Log.e("MainActivity", "Error: ${t.message}")
//            }
//
//        })
//    }
}