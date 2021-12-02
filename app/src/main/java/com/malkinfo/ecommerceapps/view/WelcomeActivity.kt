package com.malkinfo.ecommerceapps.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import com.malkinfo.ecommerceapps.MainActivity
import com.malkinfo.ecommerceapps.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val actionBar :ActionBar? = supportActionBar
        actionBar?.hide()

        val imageView = findViewById<ImageView>(R.id.imageView1)
        val ani :Animation = AnimationUtils.loadAnimation(
            this@WelcomeActivity,R.anim.left_to_right
        )
        imageView.setAnimation(ani)
        try {
            Handler().postDelayed(
                {
                    startActivity(Intent(
                        this@WelcomeActivity, SliderActivity::class.java))
                    finish()
                },3000
            )
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}