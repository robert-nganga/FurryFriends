package com.nganga.robert.furryfriends.feature_cat.data.remote.apis

import com.google.common.truth.Truth.assertThat
import com.nganga.robert.furryfriends.feature_cat.data.util.HttpRoutes
import com.nganga.robert.furryfriends.feature_cat.data.util.HttpClientFactory
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondOk
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import okio.buffer
import okio.source
import org.junit.Test

class BreedsApiImplTest {


    @Test
    fun `sends Correct Http Requests`() = runTest{
        val mockEngine = MockEngine{
            respond(
                content = """[]""",
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClientFactory.create(mockEngine)
        BreedsApiImpl(client).getBreeds(page = 1, pageSize = 20)

        val requestHistory = mockEngine.requestHistory
        assertThat(requestHistory.size).isEqualTo(1)
        assertThat(requestHistory.first().url.toString()).isEqualTo("${HttpRoutes.CAT_BREEDS}?page=1&limit=20")
    }


    @Test
    fun `when successful returns a list of cat items`() = runTest {
        val mockEngine = MockEngine{
            respond(
                content = """[{"weight":{"imperial":"12 - 18","metric":"5 - 8"},"id":"mcoo","name":"Maine Coon","cfa_url":"http://cfa.org/Breeds/BreedsKthruR/MaineCoon.aspx","vetstreet_url":"http://www.vetstreet.com/cats/maine-coon","vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/maine-coon","temperament":"Adaptable, Intelligent, Loving, Gentle, Independent","origin":"United States","country_codes":"US","country_code":"US","description":"They are known for their size and luxurious long coat Maine Coons are considered a gentle giant. The good-natured and affable Maine Coon adapts well to many lifestyles and personalities. She likes being with people and has the habit of following them around, but isn’t needy. Most Maine Coons love water and they can be quite good swimmers.","life_span":"12 - 15","indoor":0,"lap":1,"alt_names":"Coon Cat, Maine Cat, Maine Shag, Snowshoe Cat, American Longhair, The Gentle Giants","adaptability":5,"affection_level":5,"child_friendly":4,"dog_friendly":5,"energy_level":3,"grooming":3,"health_issues":3,"intelligence":5,"shedding_level":3,"social_needs":3,"stranger_friendly":5,"vocalisation":1,"experimental":0,"hairless":0,"natural":1,"rare":0,"rex":0,"suppressed_tail":0,"short_legs":0,"wikipedia_url":"https://en.wikipedia.org/wiki/Maine_Coon","hypoallergenic":0,"reference_image_id":"OOD3VXAQn"}]""".trimIndent(),
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }
        val client = HttpClientFactory.create(mockEngine)
        val api = BreedsApiImpl(client)
        val results = api.getBreeds(page = 1, pageSize = 20)

        assertThat(results.size).isEqualTo(1)
        assertThat(results.first().id).isEqualTo("mcoo")
    }
}