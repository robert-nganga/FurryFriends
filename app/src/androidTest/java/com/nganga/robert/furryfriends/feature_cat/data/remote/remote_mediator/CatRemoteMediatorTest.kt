package com.nganga.robert.furryfriends.feature_cat.data.remote.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.nganga.robert.furryfriends.core.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApiImpl
import com.nganga.robert.furryfriends.feature_cat.data.util.HttpClientFactory
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalPagingApi::class)
class CatRemoteMediatorTest {
    private lateinit var api: BreedsApi
    private lateinit var database: CatsDatabase

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CatsDatabase::class.java
        ).allowMainThreadQueries().build()
    }


    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        val engine = MockEngine{
            respond(
                content = """[{"weight":{"imperial":"12 - 18","metric":"5 - 8"},"id":"mcoo","name":"Maine Coon","cfa_url":"http://cfa.org/Breeds/BreedsKthruR/MaineCoon.aspx","vetstreet_url":"http://www.vetstreet.com/cats/maine-coon","vcahospitals_url":"https://vcahospitals.com/know-your-pet/cat-breeds/maine-coon","temperament":"Adaptable, Intelligent, Loving, Gentle, Independent","origin":"United States","country_codes":"US","country_code":"US","description":"They are known for their size and luxurious long coat Maine Coons are considered a gentle giant. The good-natured and affable Maine Coon adapts well to many lifestyles and personalities. She likes being with people and has the habit of following them around, but isn’t needy. Most Maine Coons love water and they can be quite good swimmers.","life_span":"12 - 15","indoor":0,"lap":1,"alt_names":"Coon Cat, Maine Cat, Maine Shag, Snowshoe Cat, American Longhair, The Gentle Giants","adaptability":5,"affection_level":5,"child_friendly":4,"dog_friendly":5,"energy_level":3,"grooming":3,"health_issues":3,"intelligence":5,"shedding_level":3,"social_needs":3,"stranger_friendly":5,"vocalisation":1,"experimental":0,"hairless":0,"natural":1,"rare":0,"rex":0,"suppressed_tail":0,"short_legs":0,"wikipedia_url":"https://en.wikipedia.org/wiki/Maine_Coon","hypoallergenic":0,"reference_image_id":"OOD3VXAQn"}]""".trimIndent(),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val api = BreedsApiImpl(HttpClientFactory.create(engine))
        val remoteMediator = CatRemoteMediator(api, database)
        val pagingState = PagingState<Int, CatEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertEquals(true, result is RemoteMediator.MediatorResult.Success)
        assertEquals(false, (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }


    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        val engine = MockEngine{
            respond(
                content = """[]""",
                headers = headersOf(HttpHeaders.ContentType, "application/json")

            )
        }
        val api = BreedsApiImpl(HttpClientFactory.create(engine))
        val remoteMediator = CatRemoteMediator(api, database)
        val pagingState = PagingState<Int, CatEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertEquals(true, result is RemoteMediator.MediatorResult.Success)
        assertEquals(true, (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        val engine = MockEngine{
            respondError(
                status = HttpStatusCode.Forbidden,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val api = BreedsApiImpl(HttpClientFactory.create(engine))
        val remoteMediator = CatRemoteMediator(api, database)
        val pagingState = PagingState<Int, CatEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertEquals(true, result is RemoteMediator.MediatorResult.Error)
    }

    @After
    fun tearDown(){
        database.clearAllTables()
        database.close()
    }
}