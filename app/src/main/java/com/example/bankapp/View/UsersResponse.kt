package com.example.bankapp.View

import com.example.bankapp.Users.models.User

data class UsersResponse(
    val message: String? = null,
    val userId: Long? = null,
    val isSuccessful: Boolean? = null,
    val token: String? = null
        )