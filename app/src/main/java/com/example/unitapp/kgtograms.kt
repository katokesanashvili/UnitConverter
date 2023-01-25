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
import org.w3c.dom.Text

class kgtograms: Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kgtograms, container, false)
        val convertBtn = view.findViewById<Button>(R.id.convertbutton)
        val enterkgs = view.findViewById<EditText>(R.id.enterkilograms)
        val result = view.findViewById<TextView>(R.id.kgtograms)
        val main = 1000

        convertBtn.setOnClickListener {
            val kgs = enterkgs.text.toString().toDouble()
            val gram = kgs*main
            result.text = "$gram"
        }





        return view
    }
}

