package com.example.testproject // Ensure this matches your project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    // Existing views
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var spinner: Spinner
    private lateinit var terms: CheckBox
    private lateinit var confirm: Button

    // New views from XML
    private lateinit var dateBtn: Button
    private lateinit var timeBtn: Button
    private lateinit var genderGroup: RadioGroup

    private var selectedDate = ""
    private var selectedTime = ""

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookappointment)

        // Initialize all views
        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        email = findViewById(R.id.email)
        spinner = findViewById(R.id.spinnerType)
        terms = findViewById(R.id.terms)
        confirm = findViewById(R.id.confirmBtn)
        dateBtn = findViewById(R.id.dateBtn)
        timeBtn = findViewById(R.id.timeBtn)
        genderGroup = findViewById(R.id.genderGroup)

        // Setup Spinner
        val options = arrayOf(
            "Doctor Consultation",
            "Dentist Appointment",
            "Eye Specialist",
            "Skin Specialist",
            "General Checkup"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        spinner.adapter = adapter

        // Date Picker Logic
        dateBtn.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                selectedDate = "$day/${month + 1}/$year"
                dateBtn.text = selectedDate
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Time Picker Logic
        timeBtn.setOnClickListener {
            val c = Calendar.getInstance()
            TimePickerDialog(this, { _, hour, minute ->
                selectedTime = String.format("%02d:%02d", hour, minute)
                timeBtn.text = selectedTime
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()
        }

        // Confirm Button Logic
        confirm.setOnClickListener {
            if (validateInputs()) {
                val selectedGenderId = genderGroup.checkedRadioButtonId
                val genderText = findViewById<RadioButton>(selectedGenderId)?.text.toString()

                val intent = Intent(this, ConfirmationActivity::class.java).apply {
                    putExtra("name", name.text.toString())
                    putExtra("phone", phone.text.toString())
                    putExtra("email", email.text.toString())
                    putExtra("type", spinner.selectedItem.toString())
                    putExtra("date", selectedDate)
                    putExtra("time", selectedTime)
                    putExtra("gender", genderText)
                }
                startActivity(intent)
            }
        }
    }

    private fun validateInputs(): Boolean {
        if (name.text.toString().isEmpty()) {
            name.error = "Enter Name"
            return false
        }
        if (phone.text.toString().isEmpty()) {
            phone.error = "Enter Phone"
            return false
        }
        if (email.text.toString().isEmpty()) {
            email.error = "Enter Email"
            return false
        }
        if (selectedDate.isEmpty()) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            return false
        }
        if (genderGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!terms.isChecked) {
            Toast.makeText(this, "Accept Terms", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}