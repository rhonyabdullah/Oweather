
import com.droid.silverbullet.common.model.data.CurrentWeatherData
import com.droid.silverbullet.domain.repository.CurrentWeatherRepository
import com.droid.silverbullet.domain.usecase.GetCurrentWeatherByGeoUseCase
import com.droid.silverbullet.domain.usecase.UseCaseResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-21.
 */
class GetCurrentWeatherByGeoTest {

    @MockK lateinit var repository: CurrentWeatherRepository
    @MockK lateinit var useCase: GetCurrentWeatherByGeoUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetCurrentWeatherByGeoUseCase(repository)
    }

    @Test
    fun `retrieve current weather data from geographic lat long`() = runBlocking {
        //        given
        val response = mockk<UseCaseResponse<CurrentWeatherData>>()
        val params = GetCurrentWeatherByGeoUseCase.Params(latitude = latitudeTest, longitude = longitudeTest)
        every { runBlocking { repository.getWeatherByGeographic(latitudeTest, longitudeTest) } } returns response

        //        when
        val result = useCase.execute(params)

        //        then
        result shouldEqual response
        verify { runBlocking { useCase.build(params) } }
    }
}
