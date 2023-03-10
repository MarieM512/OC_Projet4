package com.metay.mareu.ui.meeting_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.projet4.databinding.ActivityAddMeetingBinding;
import com.metay.mareu.MainActivity;
import com.metay.mareu.injection.ViewModelFactory;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Activity to add a new meeting
 */
public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    final Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog mTimePickerDialog;
    Room currentRoom;
    String myFormat = "dd/MM/yy";
    int selectedHour = 0;
    int selectedMin = 0;
    String currentDate = new SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(new Date());
    String hour = new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
    int currentHour = Integer.parseInt(hour);
    String min = new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());
    int currentMin = Integer.parseInt(min);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        MainViewModel model = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(MainViewModel.class);

        setSupportActionBar(binding.toolbar);

        // Room
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<Room>(Arrays.asList(Room.values())));
        binding.inputRoom.setAdapter(adapter);
        binding.inputRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (0 <= position && position <= 9) {
                    binding.ivRoom.setImageResource(Room.values()[position].getImage());
                    currentRoom = Room.values()[position];
                }
            }
        });

        // Date
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.FRANCE);
                binding.inputDate.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        binding.inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        // Time
        binding.inputTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog();
            }
        });

        binding.layoutGuests.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailValidator(binding.inputGuests);
            }
        });

        // Btn Cancel
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnClearGuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvGuests.setText("");
                binding.btnClearGuests.setEnabled(false);
            }
        });

        // Btn Create
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int error = 0;
                if (binding.inputName.getText().toString().equals("") || binding.inputName.length() > 25) {
                    binding.layoutName.setError("Put a name meeting");
                    error++;
                } else {
                    binding.layoutName.setError(null);
                }

                if (binding.inputDate.getText().toString().equals("")) {
                    binding.layoutDate.setError("Pick a date");
                    error++;
                } else {
                    binding.layoutDate.setError(null);
                }

                if (binding.inputTime.getText().toString().equals("")) {
                    binding.layoutTime.setError("Select a time");
                    error++;
                } else if (binding.inputDate.getText().toString().equals(currentDate)) {
                    if (selectedHour <= currentHour) {
                        if (selectedMin <= currentMin) {
                            binding.layoutTime.setError("Select an available time");
                            error++;
                        }
                    } else {
                        binding.layoutTime.setError(null);
                    }
                } else {
                    binding.layoutTime.setError(null);
                }

                if (binding.inputRoom.getText().toString().equals("")) {
                    binding.layoutRoom.setError("Chose a room");
                    error++;
                } else {
                    binding.layoutRoom.setError(null);
                }

                if (binding.tvGuests.getText().toString().equals("")) {
                    binding.scrollView.scrollTo(0, binding.scrollView.getBottom());
                    error++;
                }

                if (error == 0) {
                    addMeeting(model);
                } else {
                    Toast.makeText(AddMeetingActivity.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Add the new meeting to the list when all fields are completed
     * @param model add the new meeting with all the information
     */
    public void addMeeting(MainViewModel model) {
        Meeting meeting = new Meeting(
                binding.inputName.getText().toString(),
                binding.inputDate.getText().toString(),
                binding.inputTime.getText().toString(),
                currentRoom,
                binding.tvGuests.getText().toString()
        );
        model.addMeeting(meeting);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**
     * Display a time picker for filter the meeting list
     */
    private void timePickerDialog() {
        mTimePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String min;
                selectedHour = hourOfDay;
                selectedMin = minute;
                if (minute < 10) {
                    min = "0" + minute;
                } else {
                    min = String.valueOf(minute);
                }
                binding.inputTime.setText(hourOfDay + "h" + min);
            }
        }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true);

        mTimePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTimePickerDialog.setTitle("Select a time");
        mTimePickerDialog.show();
    }

    /**
     * Used to check if email input is valid
     *
     * @param email email of the guest
     */
    private void emailValidator(EditText email) {
        String emailToString = email.getText().toString();
        if (!emailToString.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToString).matches()) {
            if (binding.tvGuests.getText().toString() == "") {
                binding.btnClearGuests.setEnabled(true);
                binding.tvGuests.setText(emailToString);
            } else {
                binding.tvGuests.append(", " + emailToString);
            }
        } else {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Used to navigate to this activity
     *
     * @param activity activity to navigate
     */
    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
