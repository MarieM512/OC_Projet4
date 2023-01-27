package com.metay.mareu.api;


import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateMeetings;

import com.metay.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class FakeMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetingList = generateMeetings();
    private ArrayList<Meeting> mMeetings = new ArrayList<>();


    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public List<Meeting> getFakeMeeting() {
        return mMeetingList;
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
