package com.example.bankapp.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp.R
import com.example.bankapp.View.ui.login.LoginFragment
import com.example.bankapp.View.ui.login.RegistrationFragment

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.loginFragmentContainer, LoginFragment())
                .commit()
        }



    }
    fun switchToRegistration() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.loginFragmentContainer, RegistrationFragment())
            .addToBackStack(null)
            .commit()
    }

    fun switchToLogin() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.loginFragmentContainer, LoginFragment())
            .addToBackStack(null)
            .commit()
    }



}
