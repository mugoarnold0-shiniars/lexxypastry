package com.example.lexxypastry

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
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
        val appName = findViewById<TextView>(R.id.appName)

        // Bounce animation for logo
        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        logo.startAnimation(bounceAnimation)

        // Show text and start fade-in animation slightly after logo bounce begins
        Handler(Looper.getMainLooper()).postDelayed({
            appName.visibility = TextView.VISIBLE
            val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            appName.startAnimation(fadeIn)
        }, 800)

        // Fade out logo before transition
        logo.postDelayed({
            val fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
            logo.startAnimation(fadeOut)
        }, 2000)

        // Move to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}
