package com.metay.mareu.events;

import com.metay.mareu.model.Meeting;

public class DeleteFakeMeetingEvent {
    /**
     * Neighbour to delete
     */
    public Meeting mMeeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteFakeMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}
