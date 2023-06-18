package com.example.sasastapplicaton.data.db

import android.content.Context
import androidx.room.Room
import com.example.sasastapplicaton.data.db.database.AppDatabase

//Второй вариант постройки БД
object DataBaseBuilder {
    private var INSTANCE: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "geeksforgeeks-example-coroutines"
        ).build()
}
