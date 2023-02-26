package com.metay.mareu.ui.meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet4.R;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;

public class MeetingListAdapter extends ListAdapter<Meeting, MeetingListAdapter.MeetingViewHolder> {

    MeetingInterface mMeetingInterface;

    public MeetingListAdapter(@NonNull DiffUtil.ItemCallback<Meeting> diffCallback, MeetingInterface meetingInterface) {
        super(diffCallback);
        this.mMeetingInterface = meetingInterface;
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
        ImageView room;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            meeting = itemView.findViewById(R.id.item_name);
            members = itemView.findViewById(R.id.item_members);
            deleteButton = itemView.findViewById(R.id.item_delete_button);
            room = itemView.findViewById(R.id.item_image);
        }

        public void bind(Meeting meeting) {
            String title = meeting.getName() + " - " + meeting.getTime() + " - " + meeting.getRoom();
            this.meeting.setText(title);
            members.setText(meeting.getGuests());
            room.setImageResource(meeting.getImgRoom());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMeetingInterface.removeMeeting(meeting);
                }
            });
        }
    }
}
