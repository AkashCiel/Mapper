package com.example.mapper02

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class OutsideLogActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outside_log)

        val activityLog = findViewById<EditText>(R.id.editTextActivity)
        val locationLog = findViewById<EditText>(R.id.editTextLocation)
        val peopleLog = findViewById<EditText>(R.id.editTextPeople)
        val weatherLog = findViewById<EditText>(R.id.editTextWeather)

        val backButton04 = findViewById<Button>(R.id.backButton04)
        backButton04.setOnClickListener(View.OnClickListener {
            val current = Calendar.getInstance().time
            val formatted = current.toString()

            val ref = FirebaseDatabase.getInstance().getReference("OutsideLog")
            val logId = ref.push().key

            val activityEntry = activityLog.text.toString().trim()
            val locationEntry = locationLog.text.toString().trim()
            val peopleEntry = peopleLog.text.toString().trim()
            val weatherEntry = weatherLog.text.toString().trim()

            val newOutsideLog = OutsideLog(formatted, activityEntry, locationEntry, peopleEntry.toInt(), weatherEntry)
            if (logId == null){
                Toast.makeText(this@OutsideLogActivity, "Something's wrong", Toast.LENGTH_SHORT).show()}
            if (logId != null) {
                ref.child(logId).setValue(newOutsideLog).addOnCompleteListener(){}}
            returnToCheckIn()
        })
    }
    private fun returnToCheckIn(){
        val checkInIntent = Intent(this, CheckInActivity::class.java)
        startActivity(checkInIntent)
    }
}
