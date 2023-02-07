package com.metay.mareu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.projet4.databinding.ActivityMainBinding;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.di.DI;
import com.metay.mareu.events.DeleteFakeMeetingEvent;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.ui.meeting_list.AddMeetingActivity;
import com.metay.mareu.ui.meeting_list.MeetingListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MeetingListAdapter mMeetingListAdapter;
    private MeetingApiService mMeetingApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rvMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMeetingListAdapter = new MeetingListAdapter(Meeting.sItemCallback, mMeetingApiService);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMeetingActivity.navigate(MainActivity.this);
            }
        });

        mMeetingApiService = DI.getMeetingApiService();
        initList();
    }



    public void initList() {
        binding.rvMeeting.setAdapter(mMeetingListAdapter);
        mMeetingListAdapter.submitList(mMeetingApiService.getFakeMeeting());
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteFakeMeetingEvent event) {
        mMeetingApiService.deleteFakeMeeting(event.mMeeting);
        initList();
    }
}