package com.example.lexxypastry

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val logo = findViewById<ImageView>(R.id.splashImage)
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        logo.startAnimation(animation)
        val splashImage = findViewById<ImageView>(R.id.splashImage)
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        splashImage.startAnimation(bounceAnimation)

        logo.postDelayed({
            val fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
            logo.startAnimation(fadeOut)
        }, 1800)

        // Delay for 3 seconds before moving to main activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}