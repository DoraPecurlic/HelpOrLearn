package com.example.helporlearn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LogInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var createAcc: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        auth = Firebase.auth


        email = view.findViewById(R.id.logEmail)
        password = view.findViewById(R.id.logPassword)
        loginButton = view.findViewById(R.id.loginButton)
        createAcc = view.findViewById(R.id.createAccount)

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }

        loginButton.setOnClickListener{
            val enteredEmail = email.text.toString()
            val enteredPassword = password.text.toString()
            login(enteredEmail,enteredPassword)
        }
        createAcc.setOnClickListener {
            (activity as HomeActivity?)!!.replaceFragment(SignUpFragment())
        }

        return view
    }

    private fun login(enteredEmail: String, enteredPassword: String) {
        auth.signInWithEmailAndPassword(enteredEmail, enteredPassword)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Logged in",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                    (activity as HomeActivity?)!!.replaceFragment(advertisementFragment())
                } else {
                    Toast.makeText(context, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)

                }
            }

    }
    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }





}