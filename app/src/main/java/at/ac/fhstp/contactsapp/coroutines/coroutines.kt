package at.ac.fhstp.contactsapp.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis


fun main() {

    val timeInMillis = measureTimeMillis {
        runBlocking {
            getWeatherReport()
        }
    }
    println("Execution took $timeInMillis ms")
}

suspend fun getWeatherReport() {
    println("Thread " + Thread.currentThread().name)
    coroutineScope {
        val weather = async { fetchWeather() }.await()
        val temperature = async { fetchTemperature() }.await()
        println("The weather is $weather and temperature: $temperature")
    }


}

suspend fun fetchWeather(): String {
    delay(1000)
    val weather = "Sunny"
    return weather
}

suspend fun fetchTemperature(): String {
    delay(2000)
    val temperature = "15°C"
    return temperature
}