package com.sh1v4m.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
            //Toast.makeText(this,"Button Works",Toast.LENGTH_LONG).show()
        }


    }

    fun clickDatePicker(view:View){
        val myCalender= Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->
            Toast.makeText(this,"The Chosen Year Is $SelectedYear,Chosen Month is ${SelectedMonth+1} and Selected Day Is $SelectedDayOfMonth ",Toast.LENGTH_LONG).show()
            val selectedDate="$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"

            tvSelectedDate.setText(selectedDate)

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theFinalDate=sdf.parse(selectedDate)
            val selectedDateInMinutes=theFinalDate!!.time / 60000
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate!!.time / 60000
            val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

        }
            , year,month,day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }
}