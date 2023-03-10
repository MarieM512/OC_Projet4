package com.metay.mareu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.metay.mareu.api.FakeMeetingApiServiceGenerator;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.di.DI;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.repositories.MeetingRepository;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Meeting unit test
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MeetingUnitTest {

    private MeetingApiService mMeetingApiService;
    private MeetingRepository mMeetingRepository;
    private MainViewModel mViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        mMeetingApiService = DI.getNewInstanceApiService();
        mMeetingRepository = new MeetingRepository(mMeetingApiService);
        mViewModel = new MainViewModel(mMeetingRepository);
    }

    @Test
    public void meetingIsSave() {
        List<Meeting> meetings = mMeetingRepository.getMeeting();
        List<Meeting> expectedMeeting = FakeMeetingApiServiceGenerator.MEETINGS;
        assertThat(meetings, (IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray())));
    }

    @Test
    public void addMeeting() {
        Meeting meetingToAdd = mMeetingRepository.getMeeting().get(0);
        mMeetingRepository.createMeeting(meetingToAdd);
        assertTrue(mMeetingRepository.getMeeting().contains(meetingToAdd));
    }

    @Test
    public void removeMeeting() {
        Meeting meetingToDelete = mMeetingRepository.getMeeting().get(0);
        mMeetingRepository.deleteMeeting(meetingToDelete);
        assertFalse(mMeetingRepository.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void dateFilter() {
        MutableLiveData<ArrayList<Meeting>> meetings = mViewModel.getMeetingList();
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 2);
        Meeting meetingFirst = mMeetingRepository.getMeeting().get(0);
        String dateMeetingF = meetingFirst.getDate();
        String date = "05/12/23";
        assertEquals(dateMeetingF, date);
        Meeting meetingSecond = mMeetingRepository.getMeeting().get(1);
        String dateMeetingS = meetingSecond.getDate();
        assertNotEquals(dateMeetingS, date);
        mViewModel.dateFilter(date);
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 1);
        assertTrue(meetings.getValue().contains(meetingFirst));
    }

    @Test
    public void roomFilter() {
        MutableLiveData<ArrayList<Meeting>> meetings = mViewModel.getMeetingList();
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 2);
        Meeting meetingFirst = mMeetingRepository.getMeeting().get(0);
        String roomMeetingF = meetingFirst.getRoom();
        String room = Room.MARIO.getRoom();
        assertEquals(roomMeetingF, room);
        Meeting meetingSecond = mMeetingRepository.getMeeting().get(1);
        String roomMeetingS = meetingSecond.getRoom();
        assertNotEquals(roomMeetingS, room);
        mViewModel.roomFilter(room);
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 1);
        assertTrue(meetings.getValue().contains(meetingFirst));
    }

    @Test
    public void clearFilter() {
        MutableLiveData<ArrayList<Meeting>> meetings = mViewModel.getMeetingList();
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 2);
        String room = Room.MARIO.getRoom();
        mViewModel.roomFilter(room);
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 1);
        mViewModel.getMeetingList();
        assertEquals(Objects.requireNonNull(meetings.getValue()).size(), 2);
    }
}