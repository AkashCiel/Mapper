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

class DreamLogActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dream_log)

        val keyWord01 = findViewById<EditText>(R.id.dreamKeyWord01)
        val keyWord02 = findViewById<EditText>(R.id.dreamKeyWord02)
        val keyWord03 = findViewById<EditText>(R.id.dreamKeyWord03)
        val keyWord04 = findViewById<EditText>(R.id.dreamKeyWord04)
        val keyWord05 = findViewById<EditText>(R.id.dreamKeyWord05)

        val backButton02 = findViewById<Button>(R.id.backButton02)
        backButton02.setOnClickListener(View.OnClickListener {

            // Capture system date time and store in string
            val current = Calendar.getInstance().time
            val formatted = current.toString()

            // Refer to database path and generate log id
            val ref = FirebaseDatabase.getInstance().getReference("DreamLog")
            val logId = ref.push().key

            // Capture all dream keywords
            val dreamLog01 = keyWord01.text.toString().trim()
            val dreamLog02 = keyWord02.text.toString().trim()
            val dreamLog03 = keyWord03.text.toString().trim()
            val dreamLog04 = keyWord04.text.toString().trim()
            val dreamLog05 = keyWord05.text.toString().trim()

            // Initialise new object of DreamLog class
            val newDreamLog = DreamLog(formatted, dreamLog01, dreamLog02, dreamLog03, dreamLog04, dreamLog05)

            // Save generated dream log on firebase
            if (logId == null){
                Toast.makeText(this@DreamLogActivity, "Something's wrong", Toast.LENGTH_SHORT).show()}
            if (logId != null) {
                ref.child(logId).setValue(newDreamLog).addOnCompleteListener(){}}
            returnToCheckIn()
        })
    }
    private fun returnToCheckIn(){
        val checkInIntent = Intent(this, CheckInActivity::class.java)
        startActivity(checkInIntent)
    }
}
