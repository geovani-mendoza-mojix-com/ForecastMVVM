package ai.gvs.forecastmvvm.data.repository

import ai.gvs.forecastmvvm.data.db.CurrentWeatherDao
import ai.gvs.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import ai.gvs.forecastmvvm.data.network.WeatherNetworkDataSource
import ai.gvs.forecastmvvm.data.network.response.CurrentWeatherResponse
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever {newCurrentWeaher ->
            //persist
            persistFetchedCurrentWeather(newCurrentWeaher)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData() {
        //if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            "La Paz",
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        /*val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAg)*/
        return true
    }
}