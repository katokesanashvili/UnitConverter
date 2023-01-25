package com.example.unitapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class kmtomile: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kmtomiles, container, false)
        val convertBtn = view.findViewById<Button>(R.id.convertbutton)
        val enterkm = view.findViewById<EditText>(R.id.enterkilometers)
        val result = view.findViewById<TextView>(R.id.kmtomile)

        convertBtn.setOnClickListener {
            val km = enterkm.text.toString().toDouble()
            val mile = km * 0.621371
            val rounded= "%.2f".format(mile)
            result.text = rounded

        }



        return view
    }
}