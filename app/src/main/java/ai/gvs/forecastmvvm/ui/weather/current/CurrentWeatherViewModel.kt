package ai.gvs.forecastmvvm.ui.weather.current

import ai.gvs.forecastmvvm.data.repository.ForecastRepository
import ai.gvs.forecastmvvm.internal.UnitSystem
import ai.gvs.forecastmvvm.internal.lazyDeferred
import androidx.lifecycle.ViewModel;

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC
    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
