package com.example.testv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        val btnPerso = findViewById<Button>(R.id.btnPerso)
        btnPerso.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }
        val btnProduct = findViewById<Button>(R.id.btnProduct)

        btnPerso.setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }

        btnProduct.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
