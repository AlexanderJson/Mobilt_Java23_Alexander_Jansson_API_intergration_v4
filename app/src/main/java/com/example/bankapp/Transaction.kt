package com.example.bankapp

data class Transaction(
    val id: Int = 0,
    val userId: Int = 0,
    val amount: Double,
    val category: String = "",
    val name: String = "",
    val date: String = "",
    val type: String = "",
    val isRecurring: Boolean = false,
    val createdAt: String = ""
)

