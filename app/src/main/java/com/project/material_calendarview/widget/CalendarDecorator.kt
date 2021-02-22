package com.project.material_calendarview.widget

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class CalendarDecorator(
    private val date: CalendarDay,
    private val color: Int
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return date == day
    }
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(5.0F, color))
    }
}