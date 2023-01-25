package com.example.unitapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class ctof: Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ctof, container, false)
        val convertBtn = view.findViewById<Button>(R.id.convertbutton)
        val enterc = view.findViewById<EditText>(R.id.enterfahreheit)
        val result = view.findViewById<TextView>(R.id.celsiustofahrenheit)

        convertBtn.setOnClickListener {
            val cel = enterc.text.toString().toDouble()
            val fahr = (cel*9/5)+32
            result.text = "$fahr"

        }


        return view
    }
}