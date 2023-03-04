package com.metay.mareu.ui.meeting_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.projet4.databinding.DialogRoomFilterItemBinding;
import com.metay.mareu.model.Room;

import java.util.ArrayList;

/**
 * Adapter for GridView in dialog room filter
 */
public class CustomGridAdapter extends ArrayAdapter<Room> {

    private ArrayList<Room> mRoomArrayList;

    public CustomGridAdapter(Context context, int textViewResourceId, ArrayList<Room> roomArrayList) {
        super(context, textViewResourceId, roomArrayList);
        mRoomArrayList = roomArrayList;
    }

    @Override
    public int getCount() {
        return mRoomArrayList.size();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DialogRoomFilterItemBinding binding;
        binding = DialogRoomFilterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        binding.ivFilterRoom.setImageResource(mRoomArrayList.get(position).getImage());
        binding.tvFilterRoom.setText(mRoomArrayList.get(position).getRoom());

        return binding.getRoot();
    }
}
