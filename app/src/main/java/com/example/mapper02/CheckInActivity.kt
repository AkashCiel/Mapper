package com.example.mapper02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CheckInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)
        // Define actions for dream button
        val dreamButton = findViewById<Button>(R.id.dreamButton)
        dreamButton.setOnClickListener(View.OnClickListener{
            openDreamLogger();
        })

        // Define actions for outside button
        val outsideButton = findViewById<Button>(R.id.outsideButton)
        outsideButton.setOnClickListener(View.OnClickListener {
            openOutsideLogger();
        })

        // Define actions for inside button
        val insideButton = findViewById<Button>(R.id.insideButton)
        insideButton.setOnClickListener(View.OnClickListener {
            openInsideLogger();
        })

        // Define action for back button
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener(View.OnClickListener {
            returnToMain();
        })
    }
    private fun openDreamLogger(){
        val dreamIntent = Intent(this, DreamLogActivity::class.java)
        startActivity(dreamIntent)
    }

    private fun openInsideLogger(){
        val insideIntent = Intent(this, InsideLogActivity::class.java)
        startActivity(insideIntent)
    }

    private fun openOutsideLogger(){
        val outsideIntent = Intent(this, OutsideLogActivity::class.java)
        startActivity(outsideIntent)
    }

    private fun returnToMain(){
        val backToMainIntent = Intent(this, MainActivity::class.java)
        startActivity(backToMainIntent)
    }
}
