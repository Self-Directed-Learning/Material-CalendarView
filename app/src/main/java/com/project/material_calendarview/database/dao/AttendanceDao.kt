package com.project.material_calendarview.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.material_calendarview.database.entity.AttendanceEntity

@Dao
interface AttendanceDao {

    @Query("SELECT * FROM attendance_table WHERE user_idx = :user_idx")
    fun getUserAttendanceInfo(user_idx: Int) : List<AttendanceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAttendanceInfo(attendanceEntity: AttendanceEntity)
}