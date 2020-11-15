package com.example.zadankobmi

import org.junit.Test

import org.junit.Assert.*
class BmiCountTest {
    @Test
    fun bmiValuesCorrect(){
        val bmi = BmiCounter(77.0, 179.0)
        assertEquals(24.03, bmi.countKg(), 0.01)
    }

    @Test(expected = Exception::class)
    fun bmiNegativeValue(){
        val bmi = BmiCounter(-77.0, 179.0)
        assertEquals(24.03, bmi.countKg(), 0.01)
    }

    @Test(expected = Exception::class)
    fun unexpectedValuesException(){
        val bmi = BmiCounter(0.0,0.0)
        bmi.countKg()
    }
}