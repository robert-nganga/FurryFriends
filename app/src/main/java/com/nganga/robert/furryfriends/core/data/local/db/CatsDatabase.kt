package com.nganga.robert.furryfriends.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nganga.robert.furryfriends.core.data.local.dao.CatBreedsDao
import com.nganga.robert.furryfriends.core.data.local.dao.RemoteKeysDao
import com.nganga.robert.furryfriends.core.data.local.entities.CatEntity
import com.nganga.robert.furryfriends.core.data.local.entities.RemoteKeyEntity

@Database(
    entities = [CatEntity::class, RemoteKeyEntity::class],
    version = 1,
)
abstract class CatsDatabase : RoomDatabase() {
    abstract val catBreedsDao: CatBreedsDao
    abstract val remoteKeysDao: RemoteKeysDao
}