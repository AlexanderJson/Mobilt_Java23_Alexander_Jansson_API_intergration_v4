package com.example.bankapp.Users.repository

import com.example.bankapp.API.ApiRequests
import com.example.bankapp.Users.models.User
import com.example.bankapp.View.UsersResponse
import retrofit2.Call
import retrofit2.Response

class UserRepository (private val transactionApi: ApiRequests) {


    suspend fun getUser(token: String): List<User> {
        return transactionApi.getUsers(token)
    }

    suspend fun registerUser(newUser: User): Response<Void> {
        return transactionApi.registerUser(newUser)
    }

    suspend fun authenticateUser(newUser: User): Response<UsersResponse> {
        return transactionApi.authenticateUser(newUser)
    }
}
