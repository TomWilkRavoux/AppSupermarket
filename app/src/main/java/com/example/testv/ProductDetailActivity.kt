package com.example.testv

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("image")

        val imageView = findViewById<ImageView>(R.id.productImageDetail)
        val nameView = findViewById<TextView>(R.id.productNameDetail)
        val descView = findViewById<TextView>(R.id.productDescDetail)

        nameView.text = name
        descView.text = description

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}
