package com.example.mapper02

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter
import java.util.Calendar


// Write a message to the database

class MainActivity : AppCompatActivity() {

    //private lateinit var database: DatabaseReference
    //private lateinit var distractedButton: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val distractedButton = findViewById<Button>(R.id.distractedButton)
        distractedButton.setOnClickListener(View.OnClickListener {
            saveDistractedLog();

        })

        val checkInButton = findViewById<Button>(R.id.checkInButton)
        checkInButton.setOnClickListener(View.OnClickListener {
            openCheckInActivity();
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveDistractedLog(){

        val current = Calendar.getInstance().time
        val formatted = current.toString()

        val ref = FirebaseDatabase.getInstance().getReference("DistractedLog")
        val logId = ref.push().key
        if (logId == null){Toast.makeText(this@MainActivity, "Something's wrong", Toast.LENGTH_SHORT).show()}
        if (logId != null) {
            ref.child(logId).setValue(formatted).addOnCompleteListener(){
                Toast.makeText(this@MainActivity, "Life is too short to be distracted :)", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun openCheckInActivity(){
        val checkInIntent = Intent(this, CheckInActivity::class.java)
        startActivity(checkInIntent)
    }
}
