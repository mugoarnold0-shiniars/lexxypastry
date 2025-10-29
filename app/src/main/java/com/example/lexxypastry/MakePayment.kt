package com.example.lexxypastry

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class MakePayment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_make_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Retrive the data put on the extra
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val productPhoto = intent.getStringExtra("product_photo")

//        find the text view inside of the make payment activityand replace the data
        val txtName = findViewById<TextView>(R.id.txtProductName)
        val txtCost = findViewById<TextView>(R.id.txtProductCost)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)

//        update the text view with the values passed via the intent
        txtName.text=name
        txtCost.text="Ksh $cost"
        val imageUrl = "https://Arnold254.pythonanywhere.com/static/images/$productPhoto"

        //Load image using Glide, Load Faster with Glide
        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

//        find the edit text and the button by the use of ids
        val btnPay = findViewById<Button>(R.id.pay)
        val editPhone = findViewById<EditText>(R.id.phone)

        btnPay.setOnClickListener {

            val api = "https://Arnold254.pythonanywhere.com/api/mpesa_payment"
//            get the phone number
            val phone =editPhone.text.toString().trim()

//            create a RequestParams
            val data = RequestParams()
//            put data into the RequestParams
            data.put("amount",cost)
            data.put("phone",phone)

//            import the ApiHelper
            val helper = ApiHelper(applicationContext)

//            access the post metyod inside the helper class
            helper.post(api , data)

        }
    }
}