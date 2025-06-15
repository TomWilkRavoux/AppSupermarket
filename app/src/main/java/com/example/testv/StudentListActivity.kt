package com.example.testv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        findViewById<Button>(R.id.btnStudent1).setOnClickListener {
            goToPerso("Student 1", "student1@epsi.fr")
        }
        findViewById<Button>(R.id.btnStudent2).setOnClickListener {
            val intent = Intent(this, PersoTwoActivity::class.java)
            intent.putExtra("name", "Student 2")
            intent.putExtra("email", "student2@epsi.fr")
            startActivity(intent)
        }

    }

    private fun goToPerso(name: String, email: String) {
        val intent = Intent(this, PersoActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("email", email)
        startActivity(intent)
    }
}
