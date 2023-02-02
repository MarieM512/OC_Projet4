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
import com.metay.mareu.model.Meeting;

public class MeetingListAdapter extends ListAdapter<Meeting, MeetingListAdapter.MeetingViewHolder> {

    MeetingClickInterface mMeetingClickInterface;

    public MeetingListAdapter(@NonNull DiffUtil.ItemCallback<Meeting> diffCallback, MeetingClickInterface meetingClickInterface) {
        super(diffCallback);
        this.mMeetingClickInterface = meetingClickInterface;
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

        TextView name;
        TextView time;
        TextView members;
        TextView room;
        ImageButton deleteButton;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            members = itemView.findViewById(R.id.item_members);
            deleteButton = itemView.findViewById(R.id.item_delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMeetingClickInterface.onDelete(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Meeting meeting) {
            name.setText(meeting.getName());
            members.setText(meeting.getMembers());
        }
    }

    public interface MeetingClickInterface {
        void onDelete(int position);
    }
}
