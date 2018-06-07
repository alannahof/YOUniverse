package com.example.filealan.youniverse.Layout_Classes;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.filealan.youniverse.ControlCentre;
import com.example.filealan.youniverse.MainActivity;
import com.example.filealan.youniverse.R;

import java.util.ResourceBundle;

public class Settings_Page {
    private static Settings_Page instance = null;
    Activity activity;

    public Settings_Page(Activity act){
        act.setContentView(R.layout.setting_screen);
        activity = act;
        MainActivity.layout_state = R.layout.setting_screen;
        setSettingsLayout ();
    }

    public void setSettingsLayout(){

        ImageButton change_avatar = (ImageButton) activity.findViewById (R.id.settings_avatar_change);
        Button change_name = (Button) activity.findViewById (R.id.settings_name_change);
        Button logout = (Button) activity.findViewById (R.id.settings_logout);
        final EditText name_change_selection = (EditText) activity.findViewById (R.id.change_my_name);

        ControlCentre.setLayout_FooterPage ();

        if (MainActivity.logged_in) {
            change_avatar.setImageResource (MainActivity.selected_avatar);
        }

        change_avatar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                ControlCentre.setLayout_AvatarPage ();
            }
        });

        change_name.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                MainActivity.username = name_change_selection.getText ().toString ();

                //TODO ALERT THAT NAME HAS BEEN SUCCESSFULLY CHANGED
                name_change_selection.setText ("Username Changed!");

                //This will minimise the keyboard after they entered the text in the edit text and pressed change
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        //TODO REMOVE THIS BUTTON IF API IS WOKRING
        /**THIS IS FOR TESTING PURPOSES, WILL ONLY BE IN THE APP IF WE CAN GET DATABASE AND API WORKING*/
        logout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                MainActivity.username = null;
                MainActivity.selected_avatar = 0;
                MainActivity.score = 0;
                MainActivity.tokens = 0;
                MainActivity.logged_in = false;
                MainActivity.layout_state = R.layout.activity_main;
                ControlCentre.setLayout_MainLoginPage ();
            }
        });
    }

    public static Settings_Page getInstance(Activity act) {
        instance = new Settings_Page (act);
        return instance;
    }
}
