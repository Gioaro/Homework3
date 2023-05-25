package com.example.group1FirebaseProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var registrationEmailET : EditText
    private lateinit var registrationPasswordET : EditText
    private lateinit var registrationBtn : Button
    private lateinit var registrationToLoginBtn : Button

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        init()
        listeners()
    }

    private fun listeners() {
        registrationBtn.setOnClickListener {
            val email = registrationEmailET.text.toString()
            val password = registrationPasswordET.text.toString()

            if (email.isEmpty() || password.isEmpty() ||
                !email.contains('@') || password.length < 5)
                return@setOnClickListener

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "successfully registered!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java ))
                    finish()

                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        registrationToLoginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun init(){
        registrationEmailET = findViewById(R.id.registrationEmailET)
        registrationPasswordET = findViewById(R.id.registrationPasswordET)
        registrationBtn = findViewById(R.id.registrationBtn)
        registrationToLoginBtn = findViewById(R.id.registrationToLoginBtn)
    }
}