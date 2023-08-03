package com.nganga.robert.furryfriends.feature_cat.data.repos

import com.nganga.robert.furryfriends.feature_cat.data.local.db.CatsDatabase
import com.nganga.robert.furryfriends.feature_cat.data.remote.apis.BreedsApi
import javax.inject.Inject

class CatRepositoryImpl@Inject constructor(
    private val api: BreedsApi,
    private val database: CatsDatabase
){


}