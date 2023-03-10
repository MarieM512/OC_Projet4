package com.metay.mareu;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.projet4.R;
import com.example.projet4.databinding.ActivityMainBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.metay.mareu.injection.ViewModelFactory;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.ui.meeting_list.AddMeetingActivity;
import com.metay.mareu.ui.meeting_list.CustomGridAdapter;
import com.metay.mareu.ui.meeting_list.MeetingListAdapter;
import com.metay.mareu.ui.meeting_list.MeetingInterface;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/**
 * Activity to display meeting list
 */
public class MainActivity extends AppCompatActivity implements MeetingInterface {

    private ActivityMainBinding binding;
    private MeetingListAdapter mMeetingListAdapter;
    private MainViewModel model;
    final Calendar myCalendar = Calendar.getInstance();

    // Date
    String myFormat = "dd/MM/yy";
    SimpleDateFormat dateFormat = null;
    String dateSelected = "";

    // Room
    MaterialAlertDialogBuilder roomDialog;
    AlertDialog mAlertDialog;
    GridView gridView;

    @SuppressLint({"NotifyDataSetChanged", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        binding.rvMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMeetingListAdapter = new MeetingListAdapter(Meeting.sItemCallback, this);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMeetingActivity.navigate(MainActivity.this);
            }
        });

        binding.rvMeeting.setAdapter(mMeetingListAdapter);

        model = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(MainViewModel.class);

        model.getMeetingList().observe(this, mLiveData -> {
            mMeetingListAdapter.submitList(mLiveData);
            mMeetingListAdapter.notifyDataSetChanged();
        });

        roomDialog = new MaterialAlertDialogBuilder(MainActivity.this);
        gridView = new GridView(this);
        gridView.setId(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        model.deleteMeeting(meeting);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                dateFormat = new SimpleDateFormat(myFormat, Locale.FRANCE);
                dateSelected = dateFormat.format(myCalendar.getTime());
                Toast.makeText(getApplicationContext(), dateSelected, Toast.LENGTH_SHORT).show();
                model.dateFilter(dateSelected);
            }
        };

        switch (item.getItemId()) {
            case R.id.filter_date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
                break;
            case R.id.filter_room:
                RoomFilterAlertDialog();
                break;
            case R.id.filter_clear:
                model.getMeetingList();
                Toast.makeText(this, "All filter are removed", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Display an alert dialog for select a room filter
     */
    private void RoomFilterAlertDialog() {
        ArrayList<Room> mList = new ArrayList<>(Arrays.asList(Room.values()));
        gridView.setAdapter(new CustomGridAdapter(this, R.layout.dialog_room_filter_item, mList));
        gridView.setHorizontalSpacing(8);
        gridView.setVerticalSpacing(8);
        gridView.setNumColumns(3);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model.roomFilter(mList.get(position).getRoom());
                mAlertDialog.dismiss();
            }
        });

        roomDialog
                .setTitle("Room filter")
                .setView(gridView)
                .setIcon(R.drawable.ic_baseline_meeting_room_24);
        mAlertDialog = roomDialog.create();
        mAlertDialog.show();
    }
}