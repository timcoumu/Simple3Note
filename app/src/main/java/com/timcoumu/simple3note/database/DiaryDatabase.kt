package com.timcoumu.mydirary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Diary::class], exportSchema = false)
abstract class DiaryDatabase : RoomDatabase(){
    abstract fun dirarydao(): DiaryDao
    companion object{
        private var instance: DiaryDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): DiaryDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                DiaryDatabase::class.java,
                "diary_databse.db")
                .allowMainThreadQueries()
                .build().apply {
                    instance = this
                }
        }
    }
}