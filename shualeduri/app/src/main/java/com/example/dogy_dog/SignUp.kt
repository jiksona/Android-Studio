package com.example.dogy_dog

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.register.*
import kotlinx.android.synthetic.main.sighnup.*

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sighnup)
        init()

    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        register.setOnClickListener {
            startActivity(Intent(this, Register::class.java))

        }
        login.setOnClickListener {
            onLogIn()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun onLogIn() {
        if (email.text.toString().isEmpty()) {
            email.error = "Please Enter Email Address"
            email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please Enter Valid Email Address"
            email.requestFocus()
            return
        }
        if (password.text.toString().isEmpty()) {
            password.error = "Please Enter Your Password"
            password.requestFocus()
            return
        }
        




    }
}