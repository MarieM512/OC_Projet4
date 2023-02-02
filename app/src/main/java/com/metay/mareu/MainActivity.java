package com.metay.mareu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.projet4.databinding.ActivityMainBinding;
import com.metay.mareu.api.FakeMeetingApiServiceGenerator;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.ui.meeting_list.MeetingListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MeetingListAdapter.MeetingClickInterface {

    private ActivityMainBinding binding;
    private MeetingListAdapter mMeetingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.rvMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMeetingListAdapter = new MeetingListAdapter(Meeting.sItemCallback, this);
        binding.rvMeeting.setAdapter(mMeetingListAdapter);

        mMeetingListAdapter.submitList(FakeMeetingApiServiceGenerator.generateMeetings());
    }



    public void addMeeting(View view) {

    }

    public void updateMeeting(View view) {

    }

    @Override
    public void onDelete(int position) {

    }
}