package com.metay.mareu.ui.meeting_list.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.metay.mareu.model.Meeting;
import com.metay.mareu.repositories.MeetingRepository;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Use to save current data
 */
public class MainViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Meeting>> mLiveData = new MutableLiveData<>();
    private final MeetingRepository mDataRepository;
    private final ArrayList<Meeting> mMeetingArrayList;

    public MainViewModel(MeetingRepository dataRepository) {
        this.mDataRepository = dataRepository;
        mLiveData.setValue(mDataRepository.getMeeting());
        mMeetingArrayList = mDataRepository.getMeeting();
    }

    /**
     * Fetch the list of meeting
     *
     * @return the actual list of the meeting
     */
    public MutableLiveData<ArrayList<Meeting>> getMeetingList() {
        mLiveData.setValue(mMeetingArrayList);
        return mLiveData;
    }

    /**
     * Delete a meeting to the list and display the update meeting list
     *
     * @param meeting
     */
    public void deleteMeeting(Meeting meeting) {
        mDataRepository.deleteMeeting(meeting);
        mLiveData.setValue(mMeetingArrayList);
    }

    /**
     * Add a meeting to the list and display the update meeting list
     *
     * @param meeting
     */
    public void addMeeting(Meeting meeting) {
        mDataRepository.createMeeting(meeting);
        mLiveData.setValue(mMeetingArrayList);
    }

    /**
     * Filter the meeting list with the select date
     *
     * @param date
     */
    public void dateFilter(String date) {
        ArrayList<Meeting> dateFilterList = new ArrayList<>();
        for (Meeting meeting : Objects.requireNonNull(mMeetingArrayList)) {
            if (Objects.equals(meeting.getDate(), date)) {
                dateFilterList.add(meeting);
            }
        }
        mLiveData.setValue(dateFilterList);
    }

    /**
     * Filter the meeting list with the select room
     *
     * @param room
     */
    public void roomFilter(String room) {
        ArrayList<Meeting> roomFilterList = new ArrayList<>();
        for (Meeting meeting : Objects.requireNonNull(mMeetingArrayList)) {
            if (Objects.equals(meeting.getRoom(), room)) {
                roomFilterList.add(meeting);
            }
        }
        mLiveData.setValue(roomFilterList);
    }
}
