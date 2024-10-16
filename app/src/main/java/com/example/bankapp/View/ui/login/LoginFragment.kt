package com.example.bankapp.View.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.User
import com.example.bankapp.Model.UserService
import com.example.bankapp.R
import com.example.bankapp.View.AuthActivity
import com.example.bankapp.View.LoginActivity
import com.example.bankapp.View.MainActivity
import com.example.bankapp.View.RegistrationActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var authService: UserService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menu for login -> register activities
        val loginLayout = view.findViewById<android.widget.LinearLayout>(R.id.loginMenuLayout)
        val loginMenuItem = view.findViewById<android.widget.TextView>(R.id.loginMenuItem)
        val registerMenuItem = view.findViewById<android.widget.TextView>(R.id.registerMenuItem)

        //login form
        val emailEditText = view.findViewById<EditText>(R.id.loginEmailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.loginPasswordEditText)
        val loginButton = view.findViewById<android.widget.Button>(R.id.loginButton)
        // authentication for login
        authService = ApiClient.createService(UserService::class.java)



        // go to register activity
        registerMenuItem.setOnClickListener {

            val rotationAnimation = ObjectAnimator.ofFloat(loginLayout,  "rotationY", 0f, 180f)
            rotationAnimation.duration = 500
            rotationAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (activity as? AuthActivity)?.switchToRegistration()
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

        authService.authenticateUser(user).enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                val token = response.body()?.get("token")
                if (token != null) {
                    Log.d("LoginActivity", "JWT Token: $token")

                    // sparar JWT token i shared preferences
                    val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE)
                    sharedPreferences.edit().putString("token", token).apply()

                    Toast.makeText(context, "Login lyckades!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Log.d("NULL TOKEN", "JWT Token: $token")
                    Toast.makeText(context, "Login misslyckades!", Toast.LENGTH_SHORT).show()


                }            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                Log.e("LoginActivity", "Error: ${t.message}")
            }
        }
        )
    }
}



