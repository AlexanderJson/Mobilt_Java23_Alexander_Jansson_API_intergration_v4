package com.example.bankapp.Users.Authorization

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.bankapp.API.ApiClient
import com.example.bankapp.Users.models.User
import com.example.bankapp.Users.service.UserService
import com.example.bankapp.R
import com.example.bankapp.Transactions.activity.MainActivity
import com.example.bankapp.Users.repository.UserRepository
import kotlinx.coroutines.launch
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
        authService = UserService(UserRepository(ApiClient.transactionApi))



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

        // log inp
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            lifecycleScope.launch {
                authService.loginUser(email, password,requireContext())
            }
        }
    }


}



