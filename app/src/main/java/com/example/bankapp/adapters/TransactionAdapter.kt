package com.example.bankapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.Model.Transactions
import com.example.bankapp.R

class TransactionAdapter(private val transactions: List<Transactions>) :
RecyclerView.Adapter<TransactionAdapter.TransactionViewholder>() {


    class TransactionViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val amountTextView: TextView = itemView.findViewById(R.id.amount)
        val categoryTextView: TextView =  itemView.findViewById(R.id.category)
        val dateTextView: TextView = itemView.findViewById(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transactions, parent, false)
        return TransactionViewholder(itemView)
    }

    override fun getItemCount() = transactions.size

    override fun onBindViewHolder(holder: TransactionViewholder, position: Int) {
            val transaction = transactions[position]
        holder.amountTextView.text = transaction.amount.toString()
        holder.categoryTextView.text = transaction.category
        holder.dateTextView.text = transaction.date

    }

}