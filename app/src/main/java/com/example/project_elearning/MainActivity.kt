package com.example.project_elearning

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import java.util.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etPhone: EditText
    private lateinit var btnDate: Button
    private lateinit var btnTime: Button
    private lateinit var tvDate: TextView

    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi Komponen
        etPhone = findViewById(R.id.etphone)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)
        tvDate = findViewById(R.id.tvDate)

        //listener DatePicker
        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                selectedDate = "$d/${m+1}/$y"
                updateResult()
            }, year, month, day)

            datePicker.show()
        }

        //listener TimePicker
        btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, h, m ->
                selectedTime = "$h:$m"
                updateResult()
            }, hour, minute, true)

            timePicker.show()
        }
    }

    private fun updateResult() {
        val phone = etPhone.text.toString()
        val result = "Telepon: $phone\nTanggal: $selectedDate\nWaktu: $selectedTime"
        tvDate.text = result

    }

}