package com.example.testv

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testv.adapter.ProductAdapter
import com.example.testv.model.Product
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProductListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.recyclerViewProducts)
        adapter = ProductAdapter(products)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val productsUrl = intent.getStringExtra("products_url")
        if (productsUrl != null) {
            fetchProducts(productsUrl)
        } else {
            Toast.makeText(this, "Pas d'URL", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchProducts(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@ProductListActivity, "Erreur de chargement", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val jsonArray = JSONObject(json).getJSONArray("record")

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val product = Product(
                        name = obj.getString("name"),
                        description = obj.getString("description"),
                        pictureUrl = obj.getString("picture_url")
                    )
                    products.add(product)
                }

                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}
