package com.metay.mareu.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.example.projet4.R;

import org.hamcrest.Matcher;

/**
 * ViewAction to delete a neighbour
 */
public class DeleteNeighbourViewAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on neighbour";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_delete_button);
        // Maybe check for null
        button.performClick();
    }
}
