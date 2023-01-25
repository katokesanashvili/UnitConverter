import android.annotation.SuppressLint
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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.unitapp.R
import com.example.unitapp.rstpass
import com.example.unitapp.signup
import com.example.unitapp.viewpager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class login: Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var sharedPref: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val register = view.findViewById<TextView>(R.id.registerbtn)
        val frgtPass = view.findViewById<TextView>(R.id.upgradepass)

        register.setOnClickListener {
            val myfragment = signup()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainActivity, myfragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        frgtPass.setOnClickListener {
            val dialog1 = AlertDialog.Builder(requireContext())
            dialog1.setMessage("Change Password?")
            dialog1.setPositiveButton("Yes") { dialog, which ->
                val fragment = rstpass()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.mainActivity, fragment)?.addToBackStack(null)?.commit()
            }
            val dialog: AlertDialog = dialog1.create()
            dialog.show()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()
        val email = view.findViewById<EditText>(R.id.enteremail)
        val password = view.findViewById<EditText>(R.id.enterpassword)
        val login = view.findViewById<Button>(R.id.loginbutton)

        val savedEmail = sharedPref.getString("email", "")
        val savedPassword = sharedPref.getString("password", "")
        if (savedPassword != null) {
            if (savedEmail != null) {
                if(savedEmail.isNotEmpty() && savedPassword.isNotEmpty()){
                    auth.signInWithEmailAndPassword(savedEmail, savedPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(requireContext(), "you're logged", Toast.LENGTH_SHORT).show()
                                fragmentManager?.beginTransaction()
                                    ?.replace(R.id.mainActivity, viewpager())
                                    ?.commit()
                            } else {
                                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }else {
                    login.setOnClickListener {
                        val eemail = email.text.toString()
                        val ppassword = password.text.toString()
                        if (eemail == "" && ppassword == "") {
                            Toast.makeText(requireContext(), "Empty Information", Toast.LENGTH_LONG).show()
                        } else {
                            auth.signInWithEmailAndPassword(eemail, ppassword)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(requireContext(), "you're logged", Toast.LENGTH_SHORT).show()
                                        val editor = sharedPref.edit()
                                        editor.putString("email", eemail)
                                        editor.putString("password", ppassword)
                                        editor.apply()
                                        fragmentManager?.beginTransaction()
                                            ?.replace(R.id.mainActivity, viewpager())
                                            ?.commit()
                                        val database = FirebaseDatabase.getInstance()
                                        val myRef = database.getReference("uid")
                                        myRef.setValue(auth.currentUser?.uid)
                                    } else {
                                        Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}





