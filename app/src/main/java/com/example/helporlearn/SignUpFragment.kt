package com.example.helporlearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUpButton: Button
    private lateinit var redirect: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)


        auth = Firebase.auth
        name = view.findViewById(R.id.signup_name)
        surname = view.findViewById(R.id.signup_surname)
        email = view.findViewById(R.id.signup_email)
        password = view.findViewById(R.id.signup_password)
        signUpButton = view.findViewById(R.id.signup_button)
        redirect = view.findViewById(R.id.loginRedirect)

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
        signUpButton.setOnClickListener{
            val enteredEmail = email.text.toString()
            val enteredPassword = password.text.toString()
             createAccount(enteredEmail, enteredPassword)
        }

        redirect.setOnClickListener {
            (activity as HomeActivity?)!!.replaceFragment(LogInFragment())

        }


        return  view
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(context, "Authentication success.",
                        Toast.LENGTH_SHORT).show()
                    (activity as HomeActivity?)!!.replaceFragment(advertisementFragment())
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(context, task.exception?.message,
                        Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "Authentication failed.",
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