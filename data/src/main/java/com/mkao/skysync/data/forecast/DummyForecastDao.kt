package com.mkao.skysync.data.forecast

import com.mkao.skysync.domain.model.DailyForecasts
import com.mkao.skysync.domain.model.Forecast
import com.mkao.skysync.domain.model.HourlyForecasts
import com.mkao.skysync.domain.model.Weather
import java.time.LocalDateTime
import kotlin.math.roundToInt
import kotlin.random.Random

class DummyForecastDao : ForecastDao {

    companion object {
        const val MaxTemp = 35
        const val MaxWindSpped = 20
        const val MaxHumidity = 80
        const val MaxDewPoint = 10

    }

    //Main Function to generate the complete forecast
    override suspend fun generateForecast() = Forecast()

    //Generate a list of daily forecast for 14 Days(0..13)
    //starting from today
    private fun generateDailyForecasts(): List<DailyForecasts>{
        val today = LocalDateTime.now().withMinute(0)

        return (0..13).map{ numDay ->
            //For first day(today), start from current hour otherwise start
            val startHour = if(numDay == 0) today.hour else 0
            val day = today.plusDays(numDay).withHour(dayStartHour)

            //Generate radom weather parameters for the day
            val temparature = Random.nextInt(0,MaxTemp)
            val maxTemp = (temparature *1.2f).roundToInt().coerceAtMost(maxTemp)
            val minTemp = (temparature *1.2f).roundToInt().coerceAtLeast(0)
            val windSpeed = Random.nextInt(0,MaxWindSpped)
            val humidity = Random.nextInt(0,MaxHumidity)
            val dewPoint = Random.nextInt(0,MaxDewPoint)
            val weather = generateWeather(temparature, windSpeed, humidity, dewPoint)

            DailyForecasts(
                timeStamps = day.toString(),
                hourlyForecasts = generateHourlyForecasts(
                    day,
                    maxTemp,
                    minTemp
                    temparature,
                ),
                temp = temparature,
                maxTemp = maxTemp,
                minTemp = minTemp,
                windSpeed = windSpeed,
                humidity = humidity,
            )
        }
    }

    private fun generateHourlyForecasts(
        firstTemp: Int,
        maxTemp: Int,
        minTemp: Int,
        day: LocalDateTime,
    ): List<HourlyForecasts>{
        val firstHour = day.hour

        return (firstHour..23).map { hour ->
            val hour = day.hour(hour)
            val temp = when {
                hour ==  firstHour -> firstTemp
                minTemp == maxTemp -> minTemp
                else -> Random.nextInt(
                    minTemp,
                    maxTemp
                )
            }
        }
    }

    private fun generateWeather(temperature: Int, windSpeed: Int, humidity: Int, dewPoint: Int) = when {
        humidity >= 80 && dewPoint <= 5 && temperature > 20 -> Weather.Stormy
        humidity >= 40 || windSpeed >= 15 && temperature > 15 -> Weather.Rainy
        humidity < 10 && temperature < 10 -> Weather.Snowy
        windSpeed >= 10 -> Weather.Windy
        else -> Weather.Sunny
    }
}