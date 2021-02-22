package com.project.material_calendarview.viewmodel

import androidx.lifecycle.ViewModel
import com.project.material_calendarview.widget.SingleLiveEvent

class MainViewModel : ViewModel() {

    val onSelectAttendanceEvent = SingleLiveEvent<Unit>()

    fun selectAttendanceEvent() {
        onSelectAttendanceEvent.call()
    }
}