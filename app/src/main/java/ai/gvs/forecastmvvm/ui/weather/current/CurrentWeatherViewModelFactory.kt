package ai.gvs.forecastmvvm.ui.weather.current

import ai.gvs.forecastmvvm.data.repository.ForecastRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository) as T
    }
}