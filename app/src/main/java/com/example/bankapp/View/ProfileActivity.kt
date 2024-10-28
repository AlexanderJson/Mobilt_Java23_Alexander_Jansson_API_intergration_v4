package com.example.bankapp.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bankapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    true
                }

                R.id.nav_edit_users -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)

                    true
                }

                R.id.nav_logout -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    //  userService.logoutUser()
                    true
                }

                else -> {
                    false
                }
            }
        }


    }
}