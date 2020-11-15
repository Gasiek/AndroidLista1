package com.example.zadankobmi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class BmiInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_info)
        val prevBmi = findViewById<TextView>(R.id.prevBmi)
        val previousBmi = intent.extras?.getString("bmi")
        prevBmi.text = previousBmi.toString()
        showComment(prevBmi.text.toString().toDouble())
    }

    // "Send text back" button click
    fun back(view: View) {

        // Get the text from the EditText
        val prevBmi = findViewById<TextView>(R.id.prevBmi)
        val stringToPassBack = prevBmi.text.toString()

        // Put the String to pass back into an Intent and close this activity
        val intent = Intent()
        intent.putExtra("bmi", stringToPassBack)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun showComment(bmi: Double) {
        val bmiComment = findViewById<TextView>(R.id.bmiComment)
        when {
            bmi < 18.5 -> bmiComment.text = getString(R.string.skinny)
            bmi < 25 -> bmiComment.text = getString(R.string.good_diet)
            else -> bmiComment.text = getString(R.string.fat)
        }
    }
}