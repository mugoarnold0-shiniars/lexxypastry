package com.example.lexxypastry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signinpage = findViewById<Button>(R.id.signin)

        signinpage.setOnClickListener {

            val signin = Intent(applicationContext,Signin ::class.java)
            startActivity(signin)


        }
        val signup= findViewById<Button>(R.id.signup)

        signup.setOnClickListener {

            val signuppage  = Intent(applicationContext,Signup::class.java)

            startActivity(signuppage )
       }//end listerner

           // find the text vuew by use of the ID
        val usernameTextView= findViewById<TextView>(R.id.tvUsername)
        //get the started username retuned from the API
        val prefs = getSharedPreferences("user_session",Context.MODE_PRIVATE)
        val username = prefs.getString("username","user")

        //find the textview
        usernameTextView.text="Welcome,$username"

    }
}