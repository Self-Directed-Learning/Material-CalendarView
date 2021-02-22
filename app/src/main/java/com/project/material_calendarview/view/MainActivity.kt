package com.project.material_calendarview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.project.material_calendarview.R
import com.project.material_calendarview.database.entity.AttendanceEntity
import com.project.material_calendarview.databinding.ActivityMainBinding
import com.project.material_calendarview.viewmodel.MainViewModel
import com.project.material_calendarview.widget.CalendarDecorator
import com.project.material_calendarview.widget.UserAttendanceInfoThread
import com.project.material_calendarview.widget.extension.shortToast
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observerViewModel()
        getUserAttendanceInfo()
    }

    private fun observerViewModel() {
        with(viewModel) {
            onSelectAttendanceEvent.observe(this@MainActivity, Observer {
                insertUserAttendanceInfo()
            })
        }
    }

    private fun getUserAttendanceInfo() {
        val thread = UserAttendanceInfoThread(applicationContext, 0)
        thread.start()

        while (true) {
            if (thread.isFinished) {
                if (thread.isSuccess) decorateCalendarView(thread.attendanceInfoList)
                break
            }
        }
    }
    private fun insertUserAttendanceInfo() {
        val thread = UserAttendanceInfoThread(applicationContext, 1)
        thread.start()

        while (true) {
            if (thread.isFinished) {
                if (thread.isSuccess) {
                    getUserAttendanceInfo()
                    shortToast(resources.getString(R.string.toast_insert_success))
                } else {
                    shortToast(resources.getString(R.string.toast_insert_fail))
                }
                break
            }
        }
    }

    private fun decorateCalendarView(attendanceInfoList: List<AttendanceEntity>) {
        attendanceInfoList.forEach {
            val time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.attendance_time)
            val splitTime = time.split("-")

            val date = CalendarDay.from(splitTime[0].toInt(), splitTime[1].toInt(), splitTime[2].toInt())
            binding.calendarView.addDecorator(CalendarDecorator(date, resources.getColor(R.color.color_4571E6)))
        }
    }
}