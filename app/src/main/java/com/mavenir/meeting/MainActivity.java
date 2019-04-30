package com.mavenir.meeting;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mavenir.mavenirmeeting.AgendaCalendarView;
import com.mavenir.mavenirmeeting.CalendarManager;
import com.mavenir.mavenirmeeting.CalendarPickerController;
import com.mavenir.mavenirmeeting.models.BaseCalendarEvent;
import com.mavenir.mavenirmeeting.models.CalendarEvent;
import com.mavenir.mavenirmeeting.models.DayItem;
import com.mavenir.mavenirmeeting.models.IDayItem;
import com.mavenir.mavenirmeeting.models.IWeekItem;
import com.mavenir.mavenirmeeting.models.WeekItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CalendarPickerController {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    Toolbar mToolbar;
    AgendaCalendarView mAgendaCalendarView;

    // region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar)findViewById(R.id.activity_toolbar);
        mAgendaCalendarView = (AgendaCalendarView)findViewById(R.id.agenda_calendar_view);
        setSupportActionBar(mToolbar);

        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);


        //////// This can be done once in another thread
        CalendarManager calendarManager = CalendarManager.getInstance(getApplicationContext());
        calendarManager.buildCal(minDate, maxDate, Locale.getDefault(), new DayItem(), new WeekItem());
        calendarManager.loadEvents(eventList, new BaseCalendarEvent());
        ////////

        List<CalendarEvent> readyEvents = calendarManager.getEvents();
        List<IDayItem> readyDays = calendarManager.getDays();
        List<IWeekItem> readyWeeks = calendarManager.getWeeks();
        mAgendaCalendarView.init(Locale.getDefault(), readyWeeks,readyDays,readyEvents,this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());

    }

    // endregion

    // region Interface - CalendarPickerController

    @Override
    public void onDaySelected(IDayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        }
    }

    // endregion

    // region Private Methods

    private void mockList(List<CalendarEvent> eventList) {
        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.MONTH, 1);
        /*BaseCalendarEvent event1 = new BaseCalendarEvent("Mavenir Day 1", "1 PM - 2 PM", "1 PM - 2 PM",
                ContextCompat.getColor(this, R.color.orange_dark), startTime1, endTime1, true);*/
        DrawableCalendarEvent event1 = new DrawableCalendarEvent("Visit of Manyata", "", "1 PM - 2 PM",
                ContextCompat.getColor(this, R.color.blue_dark), startTime1, endTime1, false, android.R.drawable.ic_dialog_info);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.DAY_OF_YEAR, 3);
        DrawableCalendarEvent event2 = new DrawableCalendarEvent("Visit of Manyata", "", "1 PM - 2 PM",
                ContextCompat.getColor(this, R.color.blue_dark), startTime2, endTime2, false, android.R.drawable.ic_dialog_info);
        eventList.add(event2);

        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        startTime3.set(Calendar.HOUR_OF_DAY, 14);
        startTime3.set(Calendar.MINUTE, 0);
        endTime3.set(Calendar.HOUR_OF_DAY, 15);
        endTime3.set(Calendar.MINUTE, 0);
        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Manyata", "", "1 PM - 2 PM",
                ContextCompat.getColor(this, R.color.blue_dark), startTime3, endTime3, false, android.R.drawable.ic_dialog_info);
        eventList.add(event3);
    }

    // endregion
}
