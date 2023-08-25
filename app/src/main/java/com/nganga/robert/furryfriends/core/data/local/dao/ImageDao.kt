package com.nganga.robert.furryfriends.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nganga.robert.furryfriends.core.data.local.entities.ImageEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(images: List<ImageEntity>)

    @Query("SELECT * FROM images_table WHERE catId = :id")
    fun getImages(id:String): List<ImageEntity>

    @Query("DELETE FROM images_table WHERE catId = :id")
    fun deleteImages(id: String)

    @Query("DELETE FROM images_table")
    fun deleteAllImages()


}