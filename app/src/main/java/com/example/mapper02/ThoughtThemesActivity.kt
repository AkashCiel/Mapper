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

class ThoughtThemesActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thought_themes)

        val keyword01 = findViewById<EditText>(R.id.keyword01)
        val keyword02 = findViewById<EditText>(R.id.keyword02)
        val keyword03 = findViewById<EditText>(R.id.keyword03)

        val backToInsideLog01 = findViewById<Button>(R.id.backToInsideLog01)
        backToInsideLog01.setOnClickListener(View.OnClickListener {
            val current = Calendar.getInstance().time
            val formatted = current.toString()

            val ref = FirebaseDatabase.getInstance().getReference("ThoughtThemeLog")
            val logId = ref.push().key
            val thoughtTheme01 = keyword01.text.toString().trim()
            val thoughtTheme02 = keyword02.text.toString().trim()
            val thoughtTheme03 = keyword03.text.toString().trim()
            val thoughtThemeLog = ThoughtThemes(formatted, thoughtTheme01, thoughtTheme02, thoughtTheme03)
            if (logId == null){
                Toast.makeText(this@ThoughtThemesActivity, "Something's wrong", Toast.LENGTH_SHORT).show()}
            if (logId != null) {
                ref.child(logId).setValue(thoughtThemeLog).addOnCompleteListener(){}}
            returnToInsideLog();
        })
    }

    private fun returnToInsideLog(){
        val returnIntent = Intent(this, InsideLogActivity::class.java)
        startActivity(returnIntent)
    }
}
