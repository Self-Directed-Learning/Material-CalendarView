package com.project.material_calendarview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.project.material_calendarview.database.dao.AttendanceDao
import com.project.material_calendarview.database.entity.AttendanceEntity

@Database(entities = [AttendanceEntity::class], version = 1)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun getAttendanceDao() : AttendanceDao

    companion object {
        private var INSTANCE : RoomDatabase? = null
        fun getInstance(context: Context) : RoomDatabase? {
            if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.applicationContext, RoomDatabase::class.java, "room.db").build()
            return INSTANCE
        }
    }
}