package com.nganga.robert.furryfriends.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nganga.robert.furryfriends.core.data.local.entities.RemoteKeyEntity

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    fun getRemoteKey(id: String): RemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRemoteKeys(keys: List<RemoteKeyEntity>)

    @Query("DELETE FROM remote_keys")
    fun clearAllKeys()
}