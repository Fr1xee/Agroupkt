package com.example.agroupkt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agroupkt.pojos.Products
import java.util.concurrent.Executors

@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun shopDao(): ShopDao?

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null
        private const val NUMBER_OF_THREADS = 4
        val dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        fun getDatabase(context: Context): RoomDB? {
            if (INSTANCE == null) {
                synchronized(RoomDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<RoomDB>(
                            context.applicationContext,
                            RoomDB::class.java, "database"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}