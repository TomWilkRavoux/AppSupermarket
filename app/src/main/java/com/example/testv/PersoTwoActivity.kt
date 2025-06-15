package com.example.testv

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PersoTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perso_two)

        val name = intent.getStringExtra("name") ?: "Nom inconnu"
        val email = intent.getStringExtra("email") ?: "email inconnu"

        findViewById<TextView>(R.id.userName2).text = name
        findViewById<TextView>(R.id.emailText2).text = email
    }
}
