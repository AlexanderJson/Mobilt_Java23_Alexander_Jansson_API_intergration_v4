package com.example.bankapp.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R

class TransactionAdapter(private val transactionList: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    // hämtar och sätter alla itemViews med default värden
    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amount: TextView = itemView.findViewById(R.id.transaction_amount)
        val category: TextView = itemView.findViewById(R.id.transaction_category)
        val date: TextView = itemView.findViewById(R.id.transaction_date)
        val name: TextView = itemView.findViewById(R.id.transaction_name)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    // binder data till itemViews i listan
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.name.text = transaction.name
        holder.amount.text = transaction.amount.toString()
        holder.category.text = transaction.category
        holder.date.text = transaction.date
    }

    // storlek på lista
    override fun getItemCount(): Int {
        return transactionList.size
    }
}
