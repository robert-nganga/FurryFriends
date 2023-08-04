package com.nganga.robert.furryfriends.feature_cat.data.remote.apis

import com.nganga.robert.furryfriends.feature_cat.data.remote.HttpRoutes
import com.nganga.robert.furryfriends.feature_cat.data.remote.dtos.CatDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class BreedsApiImpl@Inject constructor(
    private val client: HttpClient,
) : BreedsApi {
    override suspend fun getBreeds(page: Int, pageSize: Int): List<CatDto> {
        return client.get {
            url(HttpRoutes.CAT_BREEDS)
            parameter("page", page)
            parameter("limit", pageSize)
            // parameter("apiKey", Buil)
        }.body()
    }
}