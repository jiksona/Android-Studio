package com.example.dogy_dog

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.register.*


class Register  : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        init()

    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        REGISTERy.setOnClickListener {
            onRegister()
        }
        back.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
    }

    private fun onRegister() {
        if (EMail.text.toString().isEmpty()) {
            EMail.error = "Please Enter Email Address"
            EMail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(EMail.text.toString()).matches()) {
            EMail.error = "Please Enter Valid Email Address"
            EMail.requestFocus()
            return
        }
        if (passw.text.toString().isEmpty()) {
            passw.error = "Please Enter Your Password"
            passw.requestFocus()
            return
        }
        if (confpass.text.toString() != passw.text.toString()) {
            confpass.error = "Please Confirm Password"
            confpass.requestFocus()
            return
        }


        auth.createUserWithEmailAndPassword(
            EMail.text.toString(),
            passw.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        baseContext, "Failed To Register, Try Again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}