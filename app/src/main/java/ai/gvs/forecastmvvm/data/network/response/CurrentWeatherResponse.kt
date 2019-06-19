package ai.gvs.forecastmvvm.data.network.response

import ai.gvs.forecastmvvm.data.db.entity.CurrentWeatherEntry
import ai.gvs.forecastmvvm.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)