package com.example.bankapp.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.User
import com.example.bankapp.Model.UserService
import com.example.bankapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private lateinit var authService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<android.widget.Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.loginEmailEditText)
        val passwordEditText = findViewById<EditText>(R.id.loginPasswordEditText)


        authService = ApiClient.createService(UserService::class.java)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {

        val user = User(email = email, password = password)

        authService.authenticateUser(user).enqueue(object : Callback<Map<String, String>>{
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                val token = response.body()?.get("token")
                if (token != null) {
                Log.d("LoginActivity", "JWT Token: $token")

                // sparar JWT token i shared preferences
                val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                sharedPreferences.edit().putString("token", token).apply()


                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }else{
                Log.d("NULL TOKEN", "JWT Token: $token")

                }            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                Log.e("LoginActivity", "Error: ${t.message}")
            }
        }
        )
    }
}
