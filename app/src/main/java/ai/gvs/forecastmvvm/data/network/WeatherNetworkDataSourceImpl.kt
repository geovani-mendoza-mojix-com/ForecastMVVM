package ai.gvs.forecastmvvm.data.network

import ai.gvs.forecastmvvm.data.network.response.CurrentWeatherResponse
import ai.gvs.forecastmvvm.internal.NoConnectivityException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchedCurrentWather = apixuWeatherApiService
                .getCurrentWeather(location, languageCode)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWather)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection dude!")
        }
    }
}