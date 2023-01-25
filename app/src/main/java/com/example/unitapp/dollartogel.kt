package com.example.unitapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import login

class dollartogel: Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dollartogel, container, false)

        val convertBtn = view.findViewById<Button>(R.id.convertbutton)
        val enterc = view.findViewById<EditText>(R.id.enterdollars)
        val result = view.findViewById<TextView>(R.id.dollartogel)
        auth = FirebaseAuth.getInstance()
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        convertBtn.setOnClickListener {
            val dollar = enterc.text.toString().toDouble()
            val gel = dollar*2.64
            val roundedgelamount = "%.2f".format(gel)
            result.text = roundedgelamount

        }

        val logoutBtn = view.findViewById<Button>(R.id.logoutbutton)

        if (auth.currentUser != null) {
            logoutBtn.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Log Out")
                builder.setMessage("Are you sure you want to log out?")
                builder.setPositiveButton("Yes") { _, _ ->
                    auth.signOut()
                    val editor = sharedPref.edit()
                    editor.remove("email")
                    editor.remove("password")
                    editor.apply()
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.mainActivity, login())
                        ?.commit()
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        } else {
            logoutBtn.visibility = View.GONE
        }




        return view
    }
}