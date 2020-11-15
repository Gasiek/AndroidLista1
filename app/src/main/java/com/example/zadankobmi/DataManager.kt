package com.example.zadankobmi

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object DataManager {

    var bmiHistory = ArrayList<HistoryRecord>()//array list przechwujaca floaty

    fun addLogToHistory(log: HistoryRecord) {
        if (bmiHistory.size == 10) {
            bmiHistory.removeAt(bmiHistory.size - 1)
        }
        bmiHistory.add(0, log)
    }

    fun loadData(applicationContext: Context) {
        val database = applicationContext.getSharedPreferences("bmi_history", Context.MODE_PRIVATE)
        val gson = com.google.gson.Gson()
        val json = database.getString("bmiHistory", null)
        val type = object : TypeToken<ArrayList<HistoryRecord>>() {}.type
        if (!json.isNullOrBlank()) {
            bmiHistory = gson.fromJson(json, type)
        } else {
            bmiHistory = ArrayList<HistoryRecord>()
        }
    }

    fun saveData(applicationContext: Context) {
        val database = applicationContext.getSharedPreferences("bmi_history", Context.MODE_PRIVATE)
        val gson = com.google.gson.Gson()
        val json = gson.toJson(bmiHistory)
        database.edit().apply {
            putString("bmiHistory", json)

        }.apply()
    }
}