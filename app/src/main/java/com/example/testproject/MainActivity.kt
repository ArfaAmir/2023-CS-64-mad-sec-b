package com.example.smartappointmentbooking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testproject.BookAppointmentActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.bookBtn)

        btn.setOnClickListener {

            val intent = Intent(this, BookAppointmentActivity::class.java)
            startActivity(intent)

        }
    }
}