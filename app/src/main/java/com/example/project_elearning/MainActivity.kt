package com.example.project_elearning

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etPhone: EditText
    private lateinit var btnDate: Button
    private lateinit var btnTime: Button
    private lateinit var tvDate: TextView

    private var selectedDate: String = ""
    private var selectedTime: String = ""
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi Komponen
        try {
            etPhone = findViewById(R.id.etphone)
            btnDate = findViewById(R.id.btnDate)
            btnTime = findViewById(R.id.btnTime)
            tvDate = findViewById(R.id.tvDate)
            Log.d(TAG, "Komponen diinisialisasi.")
        } catch (e: Exception) {
            Log.e(TAG, "Kesalahan saat menginisialisasi komponen: ${e.message}")
            Toast.makeText(this, "Kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            return
        }

        // Listener untuk tombol tanggal
        btnDate.setOnClickListener {
            showDatePicker()
        }

        // Listener untuk tombol waktu
        btnTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, y, m, d ->
            selectedDate = "$d/${m + 1}/$y"
            Log.d(TAG, "Tanggal dipilih: $selectedDate")
            updateResult()
        }, year, month, day)

        datePicker.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(this, { _, h, m ->
            selectedTime = String.format("%02d:%02d", h, m)
            Log.d(TAG, "Waktu dipilih: $selectedTime")
            updateResult()
        }, hour, minute, true)

        timePicker.show()
    }

    private fun updateResult() {
        val phone = etPhone.text.toString()
        val result = "Telepon: $phone\nTanggal: $selectedDate\nWaktu: $selectedTime"
        tvDate.text = result
        Log.d(TAG, result)
        Toast.makeText(this, "Hasil diperbarui", Toast.LENGTH_SHORT).show()
    }
}