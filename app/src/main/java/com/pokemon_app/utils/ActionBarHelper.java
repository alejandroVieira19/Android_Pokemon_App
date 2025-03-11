package com.pokemon_app.utils;

import androidx.appcompat.app.AppCompatActivity;

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
}
