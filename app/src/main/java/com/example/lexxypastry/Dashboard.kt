package com.example.lexxypastry

import android.os.Bundle
import android.widget.ProgressBar
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

    }
}