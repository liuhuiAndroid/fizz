package com.sec.common.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sec.common.room.entity.AccountEntity

/**
 * 用户信息
 */
@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountEntity: AccountEntity)

    @Query("SELECT * FROM account")
    fun subscribeAccount(): LiveData<List<AccountEntity>>

    @Query("DELETE FROM account")
    suspend fun deleteAll()
}