package com.nganga.robert.furryfriends.feature_cat.data.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson

class HttpClientFactory {

    companion object{
        fun create(engine: HttpClientEngine) = HttpClient(engine){
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                gson()
            }
        }
    }
}