package com.metay.mareu.ui.meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet4.databinding.ItemRecycleviewBinding;
import com.metay.mareu.model.Meeting;

/**
 * Adapter to recycler view in {@link com.metay.mareu.MainActivity}
 */
public class MeetingListAdapter extends ListAdapter<Meeting, MeetingListAdapter.MeetingViewHolder> {

    MeetingInterface mMeetingInterface;

    public MeetingListAdapter(@NonNull DiffUtil.ItemCallback<Meeting> diffCallback, MeetingInterface meetingInterface) {
        super(diffCallback);
        this.mMeetingInterface = meetingInterface;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeetingViewHolder(ItemRecycleviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = getItem(position);
        holder.bind(meeting);
    }

    class MeetingViewHolder extends RecyclerView.ViewHolder {

        ItemRecycleviewBinding binding;

        public MeetingViewHolder(ItemRecycleviewBinding b) {
            super(b.getRoot());
            binding = b;
        }

        /**
         * Bind data in the recycler view
         *
         * @param meeting
         */
        public void bind(Meeting meeting) {
            String title = meeting.getName() + " - " + meeting.getTime() + " - " + meeting.getRoom();
            binding.itemName.setText(title);
            binding.itemMembers.setText(meeting.getGuests());
            binding.itemImage.setImageResource(meeting.getImgRoom());
            binding.itemDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMeetingInterface.removeMeeting(meeting);
                }
            });
        }
    }
}
