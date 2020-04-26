package com.example.mapper02

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EmotionsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotions)

        val excitementBar = findViewById<SeekBar>(R.id.excitementLevel)
        val joyBar = findViewById<SeekBar>(R.id.joyLevel)
        val contentmentBar = findViewById<SeekBar>(R.id.contentmentLevel)
        val surpriseBar = findViewById<SeekBar>(R.id.surpriseLevel)
        val angerBar = findViewById<SeekBar>(R.id.angerLevel)
        val sadnessBar = findViewById<SeekBar>(R.id.sadnessLevel)
        val fearBar = findViewById<SeekBar>(R.id.fearLevel)
        val disgustBar = findViewById<SeekBar>(R.id.disgustLevel)

        val backToInsideLog02 = findViewById<Button>(R.id.backToInsideLog02)
        backToInsideLog02.setOnClickListener(View.OnClickListener {
            val current = Calendar.getInstance().time
            val formatted = current.toString()

            val ref = FirebaseDatabase.getInstance().getReference("EmotionLog")
            val logId = ref.push().key
            val emotionLog = EmotionLog(formatted, excitementBar.progress, joyBar.progress, contentmentBar.progress, surpriseBar.progress,
                                        angerBar.progress, sadnessBar.progress, fearBar.progress, disgustBar.progress)
            if (logId == null){
                Toast.makeText(this@EmotionsActivity, "Something's wrong", Toast.LENGTH_SHORT).show()}
            if (logId != null) {
                ref.child(logId).setValue(emotionLog).addOnCompleteListener(){}}
            returnToInsideLog();
        })
    }
    private fun returnToInsideLog(){
        val returnIntent = Intent(this, InsideLogActivity::class.java)
        startActivity(returnIntent)
    }
}