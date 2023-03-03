package com.metay.mareu.api;


import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateFakeMeetings;
import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateMeetings;

import com.metay.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingDataApiService implements MeetingApiService {

    private final List<Meeting> mMeetingList = generateFakeMeetings();
    private final ArrayList<Meeting> mMeetings = generateMeetings();

    @Override
    public List<Meeting> getFakeMeeting() {
        return mMeetingList;
    }

    @Override
    public void createFakeMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }

    @Override
    public void deleteFakeMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

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