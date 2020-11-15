package com.example.zadankobmi

data class HistoryRecord(
    var bmi: Double,
    var mass: Double,
    var height: Double,
    var date: String,
    var isKg: Boolean
) {

    fun getHistoryItemPart1(): String {
        return "Bmi: $bmi; Date: $date"
    }

    fun getHistoryItemPart2(): String {
        var massUnit = "kg"
        var heightUnit = "cm"
        if (!isKg) {
            massUnit = "lbs"
            heightUnit = "in"
        }
        return "Mass: $mass$massUnit; Height: $height$heightUnit"
    }

}