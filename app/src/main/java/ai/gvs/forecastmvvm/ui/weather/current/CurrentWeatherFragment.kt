package ai.gvs.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ai.gvs.forecastmvvm.R
import ai.gvs.forecastmvvm.ui.base.ScopedFragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
        /*
        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
            tv.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            //val currentWeatherResponse = apiService.getCurrentWeather("La Paz").await()
            //tv.text = currentWeatherResponse.toString()
            weatherNetworkDataSource.fetchCurrentWeather("La Paz", "es")
        }*/
    }
    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            tv.text = it.toString()
        })
    }
}
