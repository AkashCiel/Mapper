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
import kotlinx.android.synthetic.main.activity_energy_level.view.*
import kotlinx.android.synthetic.main.activity_inside_log.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EnergyLevelActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_energy_level)

        val energyBar = findViewById<SeekBar>(R.id.energyBar)

        val backToInsideLog03 = findViewById<Button>(R.id.backToInsideLog03)
        backToInsideLog03.setOnClickListener(View.OnClickListener {
            val current = Calendar.getInstance().time
            val formatted = current.toString()

            val ref = FirebaseDatabase.getInstance().getReference("EnergyLevelLog")
            val logId = ref.push().key
            val energyLevelLog = EnergyLevel(formatted, energyBar.progress)
            if (logId == null){Toast.makeText(this@EnergyLevelActivity, "Something's wrong", Toast.LENGTH_SHORT).show()}
            if (logId != null) {
                ref.child(logId).setValue(energyLevelLog).addOnCompleteListener(){
                    Toast.makeText(this@EnergyLevelActivity, "Energy level is " + energyBar.progress + "%", Toast.LENGTH_SHORT).show()
                }
            returnToInsideLog();
        }})
    }

    private fun returnToInsideLog(){
        val returnIntent = Intent(this, InsideLogActivity::class.java)
        startActivity(returnIntent)
    }
}


