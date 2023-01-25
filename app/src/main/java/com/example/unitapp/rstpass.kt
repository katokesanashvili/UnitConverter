package com.example.unitapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import login

class rstpass: Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rstpass, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val emailforrst = view.findViewById<EditText>(R.id.editTextTextPersonName)
        val rstbutton = view.findViewById<Button>(R.id.passwordchange)

        rstbutton.setOnClickListener {
            val eemailforrst = emailforrst.text.toString()

            auth.sendPasswordResetEmail(eemailforrst)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Check your email", Toast.LENGTH_SHORT).show()
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.mainActivity, login())
                        ?.commit()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }


        }
    }
}