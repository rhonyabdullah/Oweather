package com.droid.silverbullet.data.source.remote

import com.droid.silverbullet.data.*
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not be null`
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
class CurrentWeatherRemoteRepositoryImplTest : BaseTest() {

    @Inject lateinit var remoteRepo: CurrentWeatherRemoteRepositoryImpl

    @Before
    override fun setUp() {
        super.setUp()
        testComponent.inject(this)
    }

    @Test
    fun `get current weather by geo using latitudeTest and longitudeTest`() {
        runBlocking {
            val response = remoteRepo.getCurrentWeatherByGeo(
                latitude = latitudeTest, longitude = longitudeTest,
                appId = appIdTest, units = unitsWeatherTest, lang = langTest
            )
            response.coord.`should not be null`()
            response.coord!!.lat `should equal` latitudeTest
            response.coord!!.lon `should equal` longitudeTest
        }
    }
}
