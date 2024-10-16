package com.example.bankapp.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.bankapp.R

class AddTransactionFragment : DialogFragment() {

    interface OnTransactionAddedListener{
        fun onTransactionAdded(amount: Double)
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
            val amount = amountEditText.text.toString().toDoubleOrNull()
            if (amount != null){
                listener?.onTransactionAdded(amount)
                dismiss()
            }
        }

        return builder.create()
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        try {
            listener = context as OnTransactionAddedListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement OnTransactionAddedListener")
        }
    }


}