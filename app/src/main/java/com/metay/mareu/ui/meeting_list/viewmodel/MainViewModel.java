package com.metay.mareu.ui.meeting_list.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.repositories.MeetingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Meeting>> mLiveData = new MutableLiveData<>();
    private final MeetingRepository mDataRepository;
    private final ArrayList<Meeting> mMeetingArrayList;

    public MainViewModel(MeetingRepository dataRepository) {
        this.mDataRepository = dataRepository;
        mLiveData.setValue(mDataRepository.getMeeting());
        mMeetingArrayList = mDataRepository.getMeeting();
    }

    public MutableLiveData<ArrayList<Meeting>> getMeetingList() {
        mLiveData.setValue(mMeetingArrayList);
        return mLiveData;
    }

    public void deleteMeeting(Meeting meeting) {
        mDataRepository.deleteMeeting(meeting);
        mLiveData.setValue(mMeetingArrayList);
    }

    public void addMeeting(Meeting meeting) {
        mDataRepository.createMeeting(meeting);
        mLiveData.setValue(mMeetingArrayList);
    }

    public void dateFilter(String date) {
        ArrayList<Meeting> dateFilterList = new ArrayList<>();
        for (Meeting meeting : Objects.requireNonNull(mLiveData.getValue())) {
            if (Objects.equals(meeting.getDate(), date)) {
                dateFilterList.add(meeting);
            }
        }
        mLiveData.setValue(dateFilterList);
    }

    public void roomFilter(String room) {
        ArrayList<Meeting> roomFilterList = new ArrayList<>();
        for (Meeting meeting : Objects.requireNonNull(mLiveData.getValue())) {
            if (Objects.equals(meeting.getRoom(), room)) {
                roomFilterList.add(meeting);
            }
        }
        mLiveData.setValue(roomFilterList);
    }
}
