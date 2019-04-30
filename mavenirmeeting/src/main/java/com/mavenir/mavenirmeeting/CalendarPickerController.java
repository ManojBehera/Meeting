package com.mavenir.mavenirmeeting;

import com.mavenir.mavenirmeeting.models.CalendarEvent;
import com.mavenir.mavenirmeeting.models.IDayItem;

import java.util.Calendar;

public interface CalendarPickerController {
    void onDaySelected(IDayItem dayItem);

    void onEventSelected(CalendarEvent event);

    void onScrollToDate(Calendar calendar);
}
