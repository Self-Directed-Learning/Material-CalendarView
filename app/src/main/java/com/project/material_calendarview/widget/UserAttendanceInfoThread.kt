package com.project.material_calendarview.widget

import android.content.Context
import com.project.material_calendarview.database.RoomDatabase
import com.project.material_calendarview.database.entity.AttendanceEntity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserAttendanceInfoThread(private val context: Context, private val type: Int) : Thread() {

    var isFinished = false
    var isSuccess = false
    var attendanceInfoList = ArrayList<AttendanceEntity>()

    override fun run() {
        if (type == 0) getUserAttendanceInfo()
        else insertUserAttendanceInfo()
    }

    private fun getUserAttendanceInfo() {
        val attendanceInfoList = RoomDatabase.getInstance(context)?.getAttendanceDao()?.getUserAttendanceInfo(1)
        this.isFinished = true
        this.attendanceInfoList = attendanceInfoList as ArrayList<AttendanceEntity>
    }

    private fun insertUserAttendanceInfo() {
        val attendanceInfoList = RoomDatabase.getInstance(context)?.getAttendanceDao()?.getUserAttendanceInfo(1)
        if (attendanceInfoList?.isNotEmpty() == true) {
            attendanceInfoList.forEach {
                val attendanceTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.attendance_time)
                val currentTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis())

                if (attendanceTime.equals(currentTime)) {
                    isFinished = true
                    return
                }
            }
        }
        val entity = AttendanceEntity(null, 1, System.currentTimeMillis())
        RoomDatabase.getInstance(context)?.getAttendanceDao()?.insertUserAttendanceInfo(entity)

        isFinished = true
        isSuccess = true
    }
}