package com.example.testv
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testv.adapter.CategoryAdapter
import com.example.testv.model.Category
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private val categories = mutableListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TEST", "MainActivity démarre")
        setContentView(R.layout.activity_main)

        Log.d("TEST", "Avant findViewById")
        recyclerView = findViewById(R.id.recyclerViewCategories)
        adapter = CategoryAdapter(categories)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fetchCategories()
    }

    private fun fetchCategories() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.jsonbin.io/v3/b/6760342bacd3cb34a8ba8657")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Erreur de chargement", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val jsonArray = JSONObject(json).getJSONArray("record")

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val category = Category(
                        categoryId = obj.getString("category_id"),
                        title = obj.getString("title"),
                        productsUrl = obj.getString("products_url")
                    )
                    categories.add(category)
                }

                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}