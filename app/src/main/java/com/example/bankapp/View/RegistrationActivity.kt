package com.example.bankapp.View

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.User
import com.example.bankapp.Model.UserAdapter
import com.example.bankapp.Model.UserService
import com.example.bankapp.R
import retrofit2.Call
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        val userService = ApiClient.createService(UserService::class.java)

        val registerButton = findViewById<android.widget.Button>(R.id.registerButton)
        val emailEditText = findViewById<android.widget.EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<android.widget.EditText>(R.id.passwordEditText)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()


            val createUser = User(
                id = null,
                email = email,
                password = password
            )

            userService.registerUser(createUser).enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d("MainActivity", "User created successfully")
                    } else {
                        Log.e("MainActivity", "Error: ${response.code()}")
                    }

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }

    }



}