package at.ac.fhstp.contactsapp.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            printWeatherReport()
        }
    }
    println("It took $time ms")
}

suspend fun printWeatherReport() {
    coroutineScope {
        val weatherDeferred = async(Dispatchers.IO) { measureWeather() }
        val temperatureDeferred = async(Dispatchers.IO) { measureTemperature() }

        val resultList = awaitAll(weatherDeferred, temperatureDeferred)

        withContext(Dispatchers.Default) {
            val weather = resultList.first()
            val temperature = resultList.last()
            println("Weather is $weather and temperature is: $temperature")
        }
    }
}

suspend fun measureWeather(): String {
    println("Measuring weather")
    delay(2000)
    return "Cloudy"
}

suspend fun measureTemperature(): String {
    println("Measuring temperature")
    delay(3000)
    return "10°C"
}