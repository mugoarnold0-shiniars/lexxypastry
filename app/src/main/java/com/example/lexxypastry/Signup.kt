package com.example.lexxypastry

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val address = findViewById<EditText>(R.id.email)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val signButton = findViewById<Button>(R.id.signup)

        signButton.setOnClickListener {
            val emailText = address.text.toString().trim()
            val usernameText = username.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val phoneText = phone.text.toString().trim()

            // ✅ Validation check
            if (usernameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Optionally, validate email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val api = "https://Arnold254.pythonanywhere.com/api/signup"
            val data = RequestParams().apply {
                put("email", emailText)
                put("password", passwordText)
                put("phone", phoneText)
                put("username", usernameText)
            }

            val helper = ApiHelper(applicationContext)
            helper.post(api, data)
        }
    }
}
