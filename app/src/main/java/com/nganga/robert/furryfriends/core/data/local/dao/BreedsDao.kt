package com.nganga.robert.furryfriends.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity

@Dao
interface BreedsDao {

    @Query("SELECT * FROM cat_breeds")
    fun getCatBreeds(): PagingSource<Int, CatEntity>

    @Query("DELETE FROM cat_breeds")
    fun clearAllBreeds()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatBreeds(breeds: List<CatEntity>)
}