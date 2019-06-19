package ai.gvs.forecastmvvm.data.repository

import ai.gvs.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import androidx.lifecycle.LiveData

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}