package com.example.zadankobmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.zadankobmi.databinding.ActivityMainBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var isKilograms: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DataManager.loadData(applicationContext)
    }


    fun showMoreBmi(view: View) {
        val intent = Intent(this, BmiInfo::class.java).apply {
            putExtra("bmi", binding.bmiTV.text.toString())
        }
        startActivityForResult(intent, 1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history_option -> {
                val intent = Intent(this, History::class.java)
                startActivityForResult(intent, 0)
                true
            }
            R.id.kg -> {
                isKilograms = true
                binding.apply {
                    heightTV.text = getString(R.string.height_cm)
                    massTV.text = getString(R.string.mass_kg)
                }
                true
            }
            R.id.lb -> {
                isKilograms = false
                binding.apply {
                    heightTV.text = getString(R.string.height_in)
                    massTV.text = getString(R.string.mass_lb)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.unit_change_menu, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.apply {
            outState.run {
                putString("massET", massET.text.toString())
                putString("heightET", heightET.text.toString())
                putString("bmiTV", bmiTV.text.toString())
            }
            super.onSaveInstanceState(outState)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.apply {
            massET.setText(savedInstanceState.getString("massET"))
            heightET.setText(savedInstanceState.getString("heightET"))
            bmiTV.text = savedInstanceState.getString("bmiTV")
        }
    }

    fun count(view: View) {
        var bmi: Double = 0.0
        binding.apply {
            when {
                heightET.text.isBlank() -> heightET.error = getString(R.string.height_is_empty)
                massET.text.isBlank() -> massET.error = getString(R.string.mass_is_empty)
                else -> {
                    val mass = massET.text.toString().toDouble()
                    val height = heightET.text.toString().toDouble()
                    val counter = BmiCounter(mass, height)
                    when {
                        isKilograms -> bmi = counter.countKg()
                        else -> bmi = counter.countLb()
                    }
                    bmi = bmi.roundTo(2)
                    val historyLog = HistoryRecord(bmi, mass, height, getCurrentDate(), isKilograms)
                    val database =
                        applicationContext.getSharedPreferences("bmi_history", Context.MODE_PRIVATE)
                    DataManager.addLogToHistory(historyLog)
                    DataManager.saveData(applicationContext)
                }
            }
            when {
                bmi < 16.0 -> bmiTV.setTextColor(Color.parseColor("#082E79"))
                bmi < 16.99 -> bmiTV.setTextColor(Color.parseColor("#4169E1"))
                bmi < 18.49 -> bmiTV.setTextColor(Color.parseColor("#ACE1AF"))
                bmi < 24.99 -> bmiTV.setTextColor(Color.parseColor("#CDEBA7"))
                bmi < 29.99 -> bmiTV.setTextColor(Color.parseColor("#FFFF99"))
                bmi < 34.99 -> bmiTV.setTextColor(Color.parseColor("#FDE456"))
                bmi < 39.99 -> bmiTV.setTextColor(Color.parseColor("#CF2929"))
                else -> bmiTV.setTextColor(Color.parseColor("#801818"))
            }
            bmiTV.text = bmi.toString()
        }
    }

    fun getCurrentDate(): String {
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormatter.isLenient = false
        val today = Date()
        return dateFormatter.format(today)
    }

    fun Double.roundTo(n: Int): Double {
        return "%.${n}f".format(this).toDouble()
    }

}