package com.metay.mareu.ui.meeting_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet4.R;
import com.metay.mareu.model.Room;

import java.util.ArrayList;

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
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.dialog_room_filter_item, null);

        ImageView imageView = convertView.findViewById(R.id.iv_filter_room);
        TextView textView = convertView.findViewById(R.id.tv_filter_room);

        imageView.setImageResource(mRoomArrayList.get(position).getImage());
        textView.setText(mRoomArrayList.get(position).getRoom());

        return convertView;
    }
}
