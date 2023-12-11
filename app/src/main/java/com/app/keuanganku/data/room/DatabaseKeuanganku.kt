package com.app.keuanganku.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.keuanganku.data.entity.DeductionItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [SalaryEntity::class, SalaryAllocation::class, DeductionItem::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseKeuanganku : RoomDatabase() {
    abstract fun dao(): KeuangankuDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseKeuanganku? = null

        fun getInstance(context: Context): DatabaseKeuanganku = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseKeuanganku::class.java,
                "databaseKeuanganku.db"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        val keuangankuDao = getInstance(context).dao()
                        keuangankuDao.insertSalary(SalaryEntity(null, 0))
                    }
                }
            }).build().apply {
                INSTANCE = this
            }
        }
    }
}