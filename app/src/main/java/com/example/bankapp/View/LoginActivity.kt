package com.example.bankapp.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
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



        //menu for login -> register activities
        val loginLayout = findViewById<android.widget.LinearLayout>(R.id.loginMenuLayout)
        val loginMenuItem = findViewById<android.widget.TextView>(R.id.loginMenuItem)
        val registerMenuItem = findViewById<android.widget.TextView>(R.id.registerMenuItem)

        //login form
        val emailEditText = findViewById<EditText>(R.id.loginEmailEditText)
        val passwordEditText = findViewById<EditText>(R.id.loginPasswordEditText)
        val loginButton = findViewById<android.widget.Button>(R.id.loginButton)
        // authentication for login
        authService = ApiClient.createService(UserService::class.java)

        // go to login activity
        loginMenuItem.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // go to register activity
        registerMenuItem.setOnClickListener {

            val rotationAnimation = ObjectAnimator.ofFloat(loginLayout,  "rotationY", 0f, 180f)
            rotationAnimation.duration = 500
            rotationAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
                    startActivity(intent)
                }
            })
            rotationAnimation.start()
        }

        // log in
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(email, password)
        }
    }


    // authenticate with JWT tokens
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

                val masterKey = MasterKey.Builder(this@LoginActivity)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()

                val sharedPreferences = EncryptedSharedPreferences.create(
                    this@LoginActivity,
                    "MyPrefs",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )

                /* sparar JWT token i shared preferences
                val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                */sharedPreferences.edit().putString("token", token).apply()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
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
