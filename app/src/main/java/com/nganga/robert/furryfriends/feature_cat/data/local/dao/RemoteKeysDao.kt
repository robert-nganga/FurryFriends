package com.nganga.robert.furryfriends.feature_cat.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nganga.robert.furryfriends.feature_cat.data.local.entities.RemoteKey

interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    fun getRemoteKey(id: String): RemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRemoteKeys(keys:List<RemoteKey>)

    @Query("DELETE FROM remote_keys")
    fun clearAllKeys()
}