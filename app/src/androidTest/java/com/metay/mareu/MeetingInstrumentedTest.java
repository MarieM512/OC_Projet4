package com.metay.mareu;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.metay.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;


import android.app.Activity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.projet4.R;
import com.metay.mareu.utils.DeleteNeighbourViewAction;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * Meeting instrumented test
 *
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingInstrumentedTest {

    private Activity mActivity;

    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * When we click on the floating button for add a meeting, a form appear
     */
    @Test
    public void A_meeting_click_newMeeting() {
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.btn_create)).check(matches(ViewMatchers.withText(R.string.create)));
    }

    /**
     * Create a new meeting
     */
    @Test
    public void B_addMeeting() {
        onView(withId(R.id.rv_meeting)).check(withItemCount(2));
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.input_name)).perform(typeText("Point dev"));
        onView(withId(R.id.input_date)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2023, 3, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.input_time)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.input_room)).perform(click());
        onView(ViewMatchers.withText("Peach")).inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView())))).perform(click());
        onView(withId(R.id.input_guests)).perform(typeText("jean.dupont@gmail.com"));
        onView(ViewMatchers.withContentDescription(R.string.clear_all_filter)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.btn_create)).perform(click());
        onView(withId(R.id.rv_meeting)).check(withItemCount(3));
    }

    /**
     * Delete a meeting
     */
    @Test
    public void C_removeMeeting() {
        onView(withId(R.id.rv_meeting)).check(withItemCount(3));
        onView(withId(R.id.rv_meeting)).perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteNeighbourViewAction()));
        onView(withId(R.id.rv_meeting)).check(withItemCount(2));
    }

    /**
     * Add a date filter
     */
    @Test
    public void D_dateFilterMeeting() {
        onView(withId(R.id.rv_meeting)).check(withItemCount(2));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(ViewMatchers.withText("Filter by date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2023, 12, 5));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.rv_meeting)).check(withItemCount(1));
    }

    /**
     * Clear all the filter
     */
    @Test
    public void E_clearAllFilter() {
        onView(withId(R.id.rv_meeting)).check(withItemCount(2));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(ViewMatchers.withText("Filter by date")).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2023, 12, 5));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.rv_meeting)).check(withItemCount(1));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(ViewMatchers.withText("Clear all filter")).perform(click());
        onView(withId(R.id.rv_meeting)).check(withItemCount(2));
    }

    /**
     * Add a room filter
     */
    @Test
    public void F_roomFilterMeeting() {
        onView(withId(R.id.rv_meeting)).check(withItemCount(2));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getContext());
        onView(ViewMatchers.withText("Filter by room")).perform(click());
        onData(anything()).inAdapterView(withId(1)).atPosition(0).perform(click());
        onView(withId(R.id.rv_meeting)).check(withItemCount(1));
    }
}