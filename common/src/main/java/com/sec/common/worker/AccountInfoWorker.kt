package com.sec.common.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sec.common.constant.ExtraKeyConst
import com.sec.common.room.FizzDatabase
import com.sec.common.room.entity.AccountEntity

/**
 * 用户数据
 */
class AccountInfoWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context.applicationContext, workerParams) {

    override suspend fun doWork(): Result {
        val json = inputData.getString(ExtraKeyConst.ACCOUNT_INFO)
        if (json.isNullOrEmpty()) {
            FizzDatabase.getInstance(applicationContext).accountDao().deleteAll()
        } else {
            FizzDatabase.getInstance(applicationContext).accountDao().insert(
                AccountEntity("0", json)
            )
        }
        return Result.success()
    }

}