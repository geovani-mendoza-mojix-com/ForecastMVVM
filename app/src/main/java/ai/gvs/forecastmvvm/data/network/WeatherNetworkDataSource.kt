package ai.gvs.forecastmvvm.data.network

import ai.gvs.forecastmvvm.data.network.response.CurrentWeatherResponse
import androidx.lifecycle.LiveData

interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}