package com.project.material_calendarview.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance_table")
class AttendanceEntity(
    @PrimaryKey val idx: Int,
    val user_idx: Int,
    val attendance_time: Long
)