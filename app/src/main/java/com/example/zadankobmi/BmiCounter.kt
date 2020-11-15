package com.example.zadankobmi

import kotlin.math.pow

class BmiCounter(
    private val mass: Double,
    private val height: Double
) {
    fun countKg(): Double {
        require(mass > 0)
        require(height > 0)
        return (10000 * mass / height.pow(2))
    }

    fun countLb(): Double {
        require(mass > 0)
        require(height > 0)
        return (mass / height.pow(2) * 703)
    }

}