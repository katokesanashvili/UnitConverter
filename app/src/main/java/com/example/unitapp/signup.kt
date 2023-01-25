package com.example.unitapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import login

class signup: Fragment() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerBtn = view.findViewById<Button>(R.id.registerbutton)
        val email = view.findViewById<EditText>(R.id.enter_email)
        val password = view.findViewById<EditText>(R.id.Createpassword)
        val confpassword = view.findViewById<EditText>(R.id.Confirmpassword)
        auth = FirebaseAuth.getInstance()


        registerBtn.setOnClickListener {
            val eemail = email.text.toString()
            val ppassword = password.text.toString()

            auth.createUserWithEmailAndPassword(eemail, ppassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.mainActivity, login())
                            ?.commit()
                        Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
                        val database = FirebaseDatabase.getInstance()
                        val myRef = database.getReference("message")
                        myRef.setValue(eemail)
                    } else {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }

        }

    }
}