package com.metay.mareu.ui.meeting_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.projet4.R;
import com.example.projet4.databinding.ActivityAddMeetingBinding;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.metay.mareu.MainActivity;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private MeetingApiService mApiService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.input_room);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<Room>(Arrays.asList(Room.values())));
        autoCompleteTextView.setAdapter(adapter);

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeeting();
                }
        });

//        String roomSelected = binding.inputRoom.getEditableText().toString();
//        Log.d("Info", roomSelected);
//        binding.ivRoom.setImageResource(Room.BOO.getImage());
    }

    /**
     * Used to navigate to this activity
     *
     * @param activity
     */
    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public void addMeeting() {
        Meeting meeting = new Meeting(
                binding.inputName.getText().toString(),
                binding.inputDate.getText().toString(),
                binding.inputTime.getText().toString(),
                binding.inputRoom.getText().toString(),
                binding.inputGuests.getText().toString()
        );
        mApiService.createFakeMeeting(meeting);
        finish();
    }

}
