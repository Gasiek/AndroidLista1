package com.example.zadankobmi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class History : AppCompatActivity() {
    lateinit var history: ArrayList<HistoryRecord>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)

        val rvHistory = findViewById<View>(R.id.bmi_history) as RecyclerView
        history = DataManager.bmiHistory
        val adapter = HistoryAdapter(history)
        rvHistory.adapter = adapter
        rvHistory.layoutManager = LinearLayoutManager(this)
    }

    fun goBack(view: View) {
        finish()
    }
}