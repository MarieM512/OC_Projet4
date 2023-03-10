package com.metay.mareu.api;

import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateMeetings;

import com.metay.mareu.model.Meeting;

import java.util.ArrayList;

/**
 * Use this class to call {@link MeetingApiService}
 */
public class MeetingDataApiService implements MeetingApiService {

    private final ArrayList<Meeting> mMeetings = generateMeetings();

    @Override
    public ArrayList<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }
}
