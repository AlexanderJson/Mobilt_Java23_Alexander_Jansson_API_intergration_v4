package com.example.bankapp.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bankapp.Model.ApiClient
import com.example.bankapp.Model.Transactions
import com.example.bankapp.R
import com.example.bankapp.SharedPreferencesUtil.getJwtToken
import com.example.bankapp.Transactions.TransactionRepository
import retrofit2.Call
import retrofit2.Response

class AddTransactionFragment : DialogFragment() {

    interface OnTransactionAddedListener {
        fun onTransactionAdded(transaction: Transactions)
    }

    private var listener: OnTransactionAddedListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_add_transaction, null)
        val addBtn = view.findViewById<android.widget.Button>(R.id.addTransactionBtn)

        builder.setView(view)

        addBtn.setOnClickListener {
            val amountEditText = view.findViewById<EditText>(R.id.amountEditText)
            val nameEditText = view.findViewById<EditText>(R.id.nameEditText)

            val amount = amountEditText.text.toString().toDoubleOrNull()
            val name = nameEditText.text.toString()
            if (amount != null && name.isNotEmpty()) {

                val transaction = Transactions(amount = amount, name = name)
                addTransaction(requireContext(), transaction)
                Log.d("AddTransactionFragment", "Transaction created with amount: $amount")
                listener?.onTransactionAdded(transaction)
                dismiss()
            }
        }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as OnTransactionAddedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnTransactionAddedListener")
        }
    }


    private fun addTransaction(context: Context, transaction: Transactions) {

        // hämtar jwt token från shared prefs
        val token = getJwtToken(context)

        if (token != null) {

            val transactionRepository = TransactionRepository(ApiClient.transactionApi)
            val authHeader = "Bearer $token"

            transactionRepository.addTransaction(authHeader,transaction)
                .enqueue(object : retrofit2.Callback<TransactionsResponse>
                {
                    override fun onResponse(
                        call: Call<TransactionsResponse>,
                        response: Response<TransactionsResponse>
                    ) {
                        if (response.isSuccessful){
                            Log.d("AddTransactionFragment", "Transaction added successfully")
                            Toast.makeText(context, "Transaction added successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.e("AddTransactionFragment", "Error adding transaction: ${response.code()}")
                            Toast.makeText(context, "Error adding transaction: ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<TransactionsResponse>, t: Throwable) {
                        Log.e("AddTransaction", "Error: ${t.message}")
                    }
                }
                )



        }
    }
}