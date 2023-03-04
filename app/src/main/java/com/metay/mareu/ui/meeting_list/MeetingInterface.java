package com.metay.mareu.ui.meeting_list;

import com.metay.mareu.model.Meeting;

/**
 * Use this interface to link {@link MeetingListAdapter} with {@link com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel}
 */
public interface MeetingInterface {

    /**
     * Remove a meeting
     *
     * @param meeting
     */
    void removeMeeting(Meeting meeting);
}
