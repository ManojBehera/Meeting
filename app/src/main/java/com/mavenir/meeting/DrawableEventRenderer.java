package com.mavenir.meeting;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mavenir.mavenirmeeting.render.EventRenderer;

public class DrawableEventRenderer extends EventRenderer<DrawableCalendarEvent> {

    // region Class - EventRenderer

    @Override
    public void render(View view, DrawableCalendarEvent event) {
        //ImageView imageView = (ImageView) view.findViewById(R.id.view_agenda_event_image);
        TextView txtTitle = (TextView) view.findViewById(com.mavenir.mavenirmeeting.R.id.view_agenda_event_title);
        TextView txtLocation = (TextView) view.findViewById(com.mavenir.mavenirmeeting.R.id.view_agenda_event_location);
        RelativeLayout descriptionContainer = (RelativeLayout) view.findViewById(com.mavenir.mavenirmeeting.R.id.view_agenda_event_description_container);
//        LinearLayout locationContainer = (LinearLayout) view.findViewById(com.mavenir.mavenirmeeting.R.id.view_agenda_event_location_container);

        descriptionContainer.setVisibility(View.VISIBLE);

        //imageView.setImageDrawable(view.getContext().getResources().getDrawable(event.getDrawableId()));

        txtTitle.setTextColor(view.getResources().getColor(android.R.color.black));

        txtTitle.setText(event.getTitle());
        txtLocation.setText(event.getLocation());
        if (event.getLocation().length() > 0) {
//            locationContainer.setVisibility(View.VISIBLE);
            txtLocation.setText(event.getLocation());
        } else {
//            locationContainer.setVisibility(View.GONE);
        }

        if (event.getTitle().equals(view.getResources().getString(com.mavenir.mavenirmeeting.R.string.agenda_event_no_events))) {
            txtTitle.setTextColor(view.getResources().getColor(android.R.color.black));
        } else {
            txtTitle.setTextColor(view.getResources().getColor(com.mavenir.mavenirmeeting.R.color.theme_text_icons));
        }
        descriptionContainer.setBackgroundColor(event.getColor());
        txtLocation.setTextColor(view.getResources().getColor(com.mavenir.mavenirmeeting.R.color.theme_text_icons));
    }

    @Override
    public int getEventLayout() {
        return R.layout.view_agenda_drawable_event1;
    }

    @Override
    public Class<DrawableCalendarEvent> getRenderType() {
        return DrawableCalendarEvent.class;
    }

    // endregion
}
