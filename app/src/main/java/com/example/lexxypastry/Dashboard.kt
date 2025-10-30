package com.example.lexxypastry

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       // find the progress bar and the recyclerView by tneir IDs
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val recycleview = findViewById<RecyclerView>(R.id.recycleView)

        //Define your url where you are fetching the products
        val url="https://Arnold254.pythonanywhere.com/api/getproduct"

//        import the helper
        val helper = ApiHelper(applicationContext)


//        We have a function called loadProduct which reQuire three  parameters
        helper.loadProducts(url,recycleview,progressBar)

        // find the text vuew by use of the ID
        val usernameTextView= findViewById<TextView>(R.id.tvUsername)
        //get the started username retuned from the API
        val prefs = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val username = prefs.getString("username","user")

        //find the textview
        usernameTextView.text="Welcome,$username"

//        find the sell button by the use of an ID
        val buttonSell = findViewById<Button>(R.id.btnSell)

        buttonSell.setOnClickListener {
            val sellpage = Intent(Intent.ACTION_VIEW,Uri.parse("https://pastry-virid.vercel.app/addproduct"))
            startActivity(sellpage)
        }//end listener
        // find the logout button by use of its id
        val logout = findViewById<Button>(R.id.btnLogout)
        logout.setOnClickListener {
            //clear the stored shared Preferences
            val prefs = getSharedPreferences("user_session", Context.MODE_PRIVATE)
            prefs.edit().clear().apply()

            //navigate to the first page
            val firetpage = Intent(applicationContext,MainActivity::class.java)
            startActivity(firetpage)
        }


    }
}