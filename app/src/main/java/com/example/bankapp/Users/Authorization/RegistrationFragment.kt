package com.example.bankapp.Users.Authorization

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.bankapp.API.ApiClient
import com.example.bankapp.Users.models.User
import com.example.bankapp.Users.service.UserService
import com.example.bankapp.R
import com.example.bankapp.Users.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var authService: UserService



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authService = UserService(UserRepository(ApiClient.transactionApi))

            val registerButton = view.findViewById<android.widget.Button>(R.id.registerButton   )
            val emailEditText = view.findViewById<android.widget.EditText>(R.id.emailEditText)
            val passwordEditText = view.findViewById<android.widget.EditText>(R.id.passwordEditText)
            val userFormLayout = view.findViewById<LinearLayout>(R.id.user_form)

            val loginMenuItem = view.findViewById<android.widget.TextView>(R.id.loginMenuItem)

        // go to register activity
        loginMenuItem.setOnClickListener {

            val rotationAnimation = ObjectAnimator.ofFloat(userFormLayout,  "rotationY", 0f, 180f)
            rotationAnimation.duration = 500
            rotationAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (activity as? AuthActivity)?.switchToLogin()
                }
            })
            rotationAnimation.start()
        }



        registerButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                lifecycleScope.launch {
                    authService.registerUser(email,password,requireContext())
                }
            }

        }



    }






