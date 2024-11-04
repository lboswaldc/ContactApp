package at.ac.fhstp.contactsapp.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    val duration = measureTimeMillis {
        runBlocking {
            printWeatherReport()
        }
    }

    println("The program took $duration ms")
}

suspend fun printWeatherReport() {
    println("1: Thread: " + Thread.currentThread().name)

    coroutineScope {
        println("2: Thread: " + Thread.currentThread().name)

        val weatherDeferred = async(Dispatchers.IO) {
            println("3: Thread: " + Thread.currentThread().name)
            getWeather()
        }
        val temperatureDeferred = async { getTemperature() }

        val weather = weatherDeferred.await()
        val temperature = temperatureDeferred.await()

        launch {
            delay(400)
            println("Hiho")
        }

        println("The weather is $weather and the temperature is $temperature")
    }
}

suspend fun getWeather(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(2000)
    return "12°C"
}