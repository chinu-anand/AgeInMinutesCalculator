package com.chinu.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvDate : TextView ?= null
    private var tvAgeInMinutes:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.datePicker)

        tvDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.result)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }


    }

    private fun clickDatePicker(){

        val myCalendar =  Calendar.getInstance()

        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "${selectedDayOfMonth}/${selectedMonth+1}/${selectedYear}"

            tvDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            val selectedDateinMinutes = theDate.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateinMinutes = currentDate.time/60000

            val differenceInMinutes = currentDateinMinutes-selectedDateinMinutes

            tvAgeInMinutes?.text = differenceInMinutes.toString()
        },
            year,month,day)

        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()
    }
}