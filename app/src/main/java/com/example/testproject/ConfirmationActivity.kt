package com.example.testproject

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val result = findViewById<TextView>(R.id.result)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val type = intent.getStringExtra("type")

        result.text = """
            Booking Confirmed
            
            Name: $name
            Phone: $phone
            Email: $email
            Appointment: $type
        """.trimIndent()

    }
}