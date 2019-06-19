package ai.gvs.forecastmvvm.data.db

import ai.gvs.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import ai.gvs.forecastmvvm.data.db.entity.CurrentWeatherEntry
import ai.gvs.forecastmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry
import ai.gvs.forecastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query(value = "select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query(value = "select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}