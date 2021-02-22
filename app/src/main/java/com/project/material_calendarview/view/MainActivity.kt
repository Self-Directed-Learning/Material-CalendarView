package com.project.material_calendarview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.project.material_calendarview.R
import com.project.material_calendarview.database.RoomDatabase
import com.project.material_calendarview.database.entity.AttendanceEntity
import com.project.material_calendarview.databinding.ActivityMainBinding
import com.project.material_calendarview.viewmodel.MainViewModel
import com.project.material_calendarview.widget.CalendarDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

/**
 * RoomDatabase 테스트
 * user_id : 1
 */

//private fun insertUserAttendanceInfo() {
//    Thread {
//        val entity = AttendanceEntity(null, 1, 1613957837501)
//        RoomDatabase.getInstance(applicationContext)?.getAttendanceDao()?.insertUserAttendanceInfo(entity)
//    }.start()
//}

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        getUserAttendanceInfo()
    }

    private fun getUserAttendanceInfo() {
        Thread {
            val attendanceInfoList = RoomDatabase.getInstance(applicationContext)?.getAttendanceDao()?.getUserAttendanceInfo(1)
            if (attendanceInfoList?.isNotEmpty() == true) {
                attendanceInfoList.forEach {
                    val time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.attendance_time)
                    val splitTime = time.split("-")
                    val date = CalendarDay.from(splitTime[0].toInt(), splitTime[1].toInt(), splitTime[2].toInt())
                    binding.calendarView.addDecorator(CalendarDecorator(date, resources.getColor(R.color.color_4571E6)))
                }
            }
        }.start()
    }
}