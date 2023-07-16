package com.app.keuanganku.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.keuanganku.data.entity.AllocationItem
import com.app.keuanganku.data.entity.SalaryAllocation
import com.app.keuanganku.data.entity.SalaryEntity

@Database(
    entities = [SalaryEntity::class, SalaryAllocation::class, AllocationItem::class],
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
            ).build().apply {
                INSTANCE = this
            }
        }
    }
}