package com.droid.silverbullet.data.repository

import com.droid.silverbullet.common.model.data.CurrentWeatherHourlyData
import com.droid.silverbullet.data.BaseTest
import com.droid.silverbullet.data.latitudeTest
import com.droid.silverbullet.data.longitudeTest
import com.droid.silverbullet.domain.usecase.UseCaseResponse
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should not be empty`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

/**
 * Project OWeather.
 *
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
@RunWith(MockitoJUnitRunner::class)
class CurrentWeatherRepositoryImplTest : BaseTest() {

    @Inject lateinit var repository: CurrentWeatherRepositoryImpl

    @Before
    override fun setUp() {
        super.setUp()
        testComponent.inject(this)
    }

    @Test
    fun `verify get weather from remote repository`() {
        runBlocking {
            //        given
            val latitude = latitudeTest
            val longitude = longitudeTest

//        when
            val result = repository.getWeatherByGeographic(latitude, longitude)

//        then
            result `should be instance of` UseCaseResponse.Success::class.java
        }
    }

    @Test
    fun `retrieve hourly weather by geo location`() {
        runBlocking {
//            given
            val latitude = latitudeTest
            val longitude = longitudeTest

//            when
            val result = repository.getHourlyByGeographic(latitude, longitude)

//            then
            result `should be instance of` UseCaseResponse.Success::class.java
            (result as UseCaseResponse.Success<CurrentWeatherHourlyData>).value.weatherInHourList.`should not be empty`()
        }
    }
}