package com.malkinfo.ecommerceapps.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.malkinfo.ecommerceapps.MainActivity
import com.malkinfo.ecommerceapps.R
import kotlinx.android.synthetic.main.activity_verification.*

class VerificationActivity : AppCompatActivity() {

    var auth :FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        auth = FirebaseAuth.getInstance()
        if (auth!!.currentUser != null){

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        supportActionBar?.hide()
        editNumber.requestFocus()
        continueBtn.setOnClickListener {
            val intent = Intent(this,OtpActivity::class.java)
            intent.putExtra("phoneNumber",editNumber.text.toString())
            startActivity(intent)
        }




    }
}