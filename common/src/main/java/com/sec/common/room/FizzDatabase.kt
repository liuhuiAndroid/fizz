package com.sec.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sec.common.ktx.SingletonHolder
import com.sec.common.room.dao.AccountDao
import com.sec.common.room.entity.AccountEntity

@Database(
    entities = [AccountEntity::class],
    version = 1
)
abstract class FizzDatabase : RoomDatabase() {

    companion object : SingletonHolder<FizzDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, FizzDatabase::class.java, "fizz-refactor")
            .fallbackToDestructiveMigration()
            .build()
    })

    abstract fun accountDao(): AccountDao
}