package com.nganga.robert.furryfriends.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedsDao {

    @Query("SELECT * FROM cat_breeds")
    fun getCatBreeds(): PagingSource<Int, CatEntity>

    @Query("SELECT * FROM cat_breeds WHERE id = :id")
    fun getCatById(id: String): Flow<List<CatEntity>>

    @Query("DELETE FROM cat_breeds")
    fun clearAllBreeds()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatBreeds(breeds: List<CatEntity>)
}