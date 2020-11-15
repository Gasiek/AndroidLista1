package com.example.zadankobmi

import android.content.Context

object DataManager {

    var bmiHistory = ArrayList<HistoryRecord>()//array list przechwujaca floaty

    fun addLogToHistory(log: HistoryRecord){
        if(bmiHistory.size == 10){
            bmiHistory.removeAt(bmiHistory.size - 1)
        }
        bmiHistory.add(0,log)
    }

    fun loadData(applicationContext:Context) {
        var database = applicationContext.getSharedPreferences("bmi_history", Context.MODE_PRIVATE )
        var weight = database.getString("savedWeight", null)
        var height = database.getString("savedHeight", null)
        var date = database.getString("savedDate", null)
        var bmi = database.getString("savedBmi", null)
        bmiHistory = ArrayList<HistoryRecord>()
    }

    fun saveData(applicationContext: Context){
        val database =
            applicationContext.getSharedPreferences("bmi_history", Context.MODE_PRIVATE)
        database.edit().apply {
            putString("savedWeight", mass.toString())
            putString("savedHeight", height.toString())
            putString("savedDate", getCurrentDate())
            putString("savedBmi", bmi.toString())
        }.apply()
    }
}