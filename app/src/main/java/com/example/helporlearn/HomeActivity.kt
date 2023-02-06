package com.example.helporlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.helporlearn.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)
        auth = Firebase.auth
        val currentUser = auth.currentUser

        replaceFragment(InstructorsListFragment())



        val nav = findViewById<BottomNavigationView>(R.id.navigation)
        nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Search -> replaceFragment(InstructorsListFragment())
                R.id.PublishAd -> replaceFragment(LogInFragment())//if(currentUser != null) replaceFragment(advertisementFragment())
                                    //else{replaceFragment(LogInFragment())}
            }
            true
        }

    }
     fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }


}