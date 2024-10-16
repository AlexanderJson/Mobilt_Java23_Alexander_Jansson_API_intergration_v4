package com.example.bankapp.View.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val userService = ApiClient.createService(UserService::class.java)

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


                val createUser = User(
                    email = email,
                    password = password
                )

                userService.registerUser(createUser).enqueue(object : retrofit2.Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Log.d("MainActivity", "User created successfully")
                            Toast.makeText(context, "Registrering lyckades!", Toast.LENGTH_SHORT).show() // Lägg till UI för notis/felmeddelanden!
                            (activity as? AuthActivity)?.switchToLogin()
                        } else {
                            Log.e("MainActivity", "Error: ${response.code()}")
                            Toast.makeText(context, "Registrering misslyckades!", Toast.LENGTH_SHORT).show()

                        }

                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

            }

        }



    }






