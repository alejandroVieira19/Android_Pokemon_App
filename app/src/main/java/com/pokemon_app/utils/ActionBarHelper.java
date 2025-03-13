package com.pokemon_app.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class ActionBarHelper {
    private final AppCompatActivity activity;
    public ActionBarHelper(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void changeActionBarTitleAndShowArrowBack(String newTitle, boolean isArrowBackToShow) {
        activity.getSupportActionBar().setTitle(newTitle);
        if (isArrowBackToShow) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void changeActionBarTitleAndPopStackBack(String newTitle, FragmentManager fragmentManager) {
        activity.getSupportActionBar().setTitle(newTitle);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        fragmentManager.popBackStack();
    }
}



