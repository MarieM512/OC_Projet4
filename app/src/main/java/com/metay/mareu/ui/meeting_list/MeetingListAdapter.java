package com.metay.mareu.ui.meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet4.R;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.events.DeleteFakeMeetingEvent;
import com.metay.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

public class MeetingListAdapter extends ListAdapter<Meeting, MeetingListAdapter.MeetingViewHolder> {

    MeetingApiService mMeetingApiService;

    public MeetingListAdapter(@NonNull DiffUtil.ItemCallback<Meeting> diffCallback, MeetingApiService meetingApiService) {
        super(diffCallback);
        this.mMeetingApiService = meetingApiService;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeetingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = getItem(position);
        holder.bind(meeting);
    }

    class MeetingViewHolder extends RecyclerView.ViewHolder {

        TextView meeting;
        TextView members;
        ImageButton deleteButton;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            meeting = itemView.findViewById(R.id.item_name);
            members = itemView.findViewById(R.id.item_members);
            deleteButton = itemView.findViewById(R.id.item_delete_button);
        }

        public void bind(Meeting meeting) {
            String title = meeting.getName() + " - " + meeting.getTime() + " - " + meeting.getRoom();
            this.meeting.setText(title);
            members.setText(meeting.getGuests());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteFakeMeetingEvent(meeting));
                    // mMeetingApiService.deleteMeeting(meeting);
                }
            });
        }
    }
}
