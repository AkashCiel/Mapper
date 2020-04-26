package com.example.mapper02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class InsideLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inside_log)

        val thoughtThemes = findViewById<Button>(R.id.thoughtThemes)
        thoughtThemes.setOnClickListener(View.OnClickListener {
            openThoughtThemes();
        })

        val emotions = findViewById<Button>(R.id.emotions)
        emotions.setOnClickListener(View.OnClickListener {
            openEmotions();
        })

        val energyLevel = findViewById<Button>(R.id.energyLevel)
        energyLevel.setOnClickListener(View.OnClickListener {
            openEnergyLevel();
        })

        val backButton03 = findViewById<Button>(R.id.backButton03)
        backButton03.setOnClickListener(View.OnClickListener {
            returnToCheckIn()
        })
    }
    private fun openThoughtThemes(){
        val thoughtThemesIntent = Intent(this, ThoughtThemesActivity::class.java)
        startActivity(thoughtThemesIntent)
    }

    private fun openEmotions(){
        val emotionsIntent = Intent(this, EmotionsActivity::class.java)
        startActivity(emotionsIntent)
    }

    private fun openEnergyLevel(){
        val energyLevelIntent = Intent(this, EnergyLevelActivity::class.java)
        startActivity(energyLevelIntent)
    }

    private fun returnToCheckIn(){
        val checkInIntent = Intent(this, CheckInActivity::class.java)
        startActivity(checkInIntent)
    }
}
