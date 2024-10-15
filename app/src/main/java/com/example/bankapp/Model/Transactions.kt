package com.example.bankapp.Model

data class Transactions(
    val id: Int,
    val userId: Int,
    val amount: Double,
    val category: String,
    val description: String,
    val date: String,
    val type: String,
    val isRecurring: Boolean,
    val createdAt: String
)
